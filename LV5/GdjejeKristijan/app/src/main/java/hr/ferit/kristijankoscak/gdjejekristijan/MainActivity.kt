package hr.ferit.kristijankoscak.gdjejekristijan

import android.Manifest
import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.media.AudioManager
import android.media.SoundPool
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.getChannelId
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.FileProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.util.*


class MainActivity : AppCompatActivity() , OnMapReadyCallback {
    private val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
    private val cameraPermission = Manifest.permission.CAMERA
    private val openImagePermission = Manifest.permission.READ_EXTERNAL_STORAGE
    private val locationRequestCode = 10
    private val cameraRequestCode = 11
    private var REQUEST_IMAGE_CAPTURE = 1
    private lateinit var mCurrentPhotoPath: String

    private lateinit var mapFragment:SupportMapFragment
    private lateinit var map: GoogleMap
    private lateinit var geocoder:Geocoder
    private lateinit var addresses: List<Address>
    private lateinit var soundHelper : SoundHelper
    private lateinit var locationManager: LocationManager
    private val locationListener = object: LocationListener {
        override fun onProviderEnabled(provider: String?) { }
        override fun onProviderDisabled(provider: String?) { }
        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) { }
        override fun onLocationChanged(location: Location?) {
            updateLocationDisplay(location)
        }
    }
    private var buttonEnabled: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeUI()
        setUpUI()
        trackLocation()
    }
    private fun initializeUI(){
        soundHelper = SoundHelper(this)
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        geocoder = Geocoder(this, Locale.getDefault())
    }
    private fun setUpUI(){
        mapFragment.getMapAsync(this)
        photo_btn.setOnClickListener { if(buttonEnabled) takePhoto() }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) createNotificationChannels()
    }
    private fun updateLocationDisplay(location: Location?) {
        val lat = location?.latitude ?: 0
        val lon = location?.longitude ?: 0
        addresses = geocoder.getFromLocation(lat as Double,lon as Double,1)
        displayData(lat,lon,addresses)
    }
    private fun displayData(latitude:Double,longitude:Double,addresses:List<Address>){
        val address = addresses[0].thoroughfare + " " + addresses[0].subThoroughfare
        val city = addresses[0].locality
        val country = addresses[0].countryName
        locationDisplay.text = "Geografska širina: $latitude\nGeografska dužina: $longitude\nDržava: $country\nMjesto: $city\nAdresa: $address"
        addMyLocationMarker(LatLng(latitude,longitude))
        buttonEnabled = true
    }
    private fun addMyLocationMarker(myPosition:LatLng){
        map.addMarker(MarkerOptions().position(myPosition).title("Ovdje sam").snippet("Wow! Konačno znam gdje se nalazim!"))
        map.mapType = GoogleMap.MAP_TYPE_SATELLITE
        map.uiSettings.isZoomControlsEnabled = true
        map.moveCamera(CameraUpdateFactory.newLatLng(myPosition))
    }
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        soundHelper.loadSounds()
        setMapClickListener(map)
    }
    private fun setMapClickListener(map:GoogleMap){
        map.setOnMapClickListener { position ->
            map.addMarker(
                MarkerOptions()
                    .position(position)
                    .title("Novi marker!")
                    .snippet("Ručno postavljen marker.")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
            )
            soundHelper.playSound()
        }
    }
    private fun trackLocation() {
        if(hasPermissionCompat(locationPermission)){
            startTrackingLocation()
        } else {
            requestPermisionCompat(arrayOf(locationPermission), locationRequestCode)
        }
    }
    private fun startTrackingLocation() {
        Log.d("TAG", "Tracking location")
        val criteria: Criteria = Criteria()
        criteria.accuracy = Criteria.ACCURACY_FINE
        val provider = locationManager.getBestProvider(criteria, true)
        val minTime = 1000L
        val minDistance = 10.0F
        try{
            locationManager.requestLocationUpdates(provider, minTime, minDistance, locationListener)
        } catch (e: SecurityException){
            Toast.makeText(this, R.string.permissionNotGranted, Toast.LENGTH_SHORT).show()
        }
    }

    private fun takePhoto(){
        if(hasPermissionCompat(cameraPermission) && hasPermissionCompat(openImagePermission)){
            openCamera()
        } else {
            requestPermisionCompat(arrayOf(openImagePermission,cameraPermission), cameraRequestCode)
        }
    }
    private fun createFile(place:String): File {
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(place, ".jpg", storageDir)
            .apply { mCurrentPhotoPath = absolutePath }
    }
    private fun openCamera(){
        val intent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val file: File = createFile(addresses[0].locality)
        val uri: Uri = FileProvider.getUriForFile(
            this,
            "com.example.android.fileprovider",
            file
        )
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            displayTakenImageNotification(mCurrentPhotoPath,this)
        }
    }
    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray) {
        when(requestCode){
            locationRequestCode -> {
                if(grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    trackLocation()
                else
                    Toast.makeText(this, R.string.permissionNotGranted, Toast.LENGTH_SHORT).show()
            }
            cameraRequestCode -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
                    openCamera()
                else
                    Toast.makeText(this, R.string.permissionNotGranted, Toast.LENGTH_SHORT).show()
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
    override fun onPause() {
        super.onPause()
        locationManager.removeUpdates(locationListener)
    }
}
