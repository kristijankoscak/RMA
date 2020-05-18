package hr.ferit.kristijankoscak.gdjejekristijan

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.FileProvider
import java.io.File

fun getChannelId(name: String): String = "${WhereAmIApp.ApplicationContext.packageName}-$name"
const val CHANNEL_NEW_IMAGE = "New_image_channel"
@RequiresApi(api = Build.VERSION_CODES.O)
fun createNotificationChannel(name: String, description: String, importance: Int): NotificationChannel {
    val channel = NotificationChannel(getChannelId(name), name, importance)
    channel.description = description
    channel.setShowBadge(true)
    return channel
}
@RequiresApi(api = Build.VERSION_CODES.O)
fun createNotificationChannels() {
    val channels = mutableListOf<NotificationChannel>()
    channels.add(createNotificationChannel(
        CHANNEL_NEW_IMAGE,
        "New image created! Tap to open.",
        NotificationManagerCompat.IMPORTANCE_DEFAULT
    ))
    val notificationManager = WhereAmIApp.ApplicationContext.getSystemService(NotificationManager::class.java)
    notificationManager.createNotificationChannels(channels)
}

fun displayTakenImageNotification(filePath:String, context: Context){
    val file = File(filePath)

    val intent = Intent(Intent.ACTION_VIEW)
        .setDataAndType(
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                FileProvider.getUriForFile(
                    WhereAmIApp.ApplicationContext,
                    "com.example.android.fileprovider",
                    file!!)
            }
            else Uri.fromFile(file), "image/*"
        )
        .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    // We can put extra in the intent.
    val pendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_CANCEL_CURRENT
    )
    val notification = NotificationCompat.Builder(context, getChannelId(CHANNEL_NEW_IMAGE))
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle("Spremljena je nova slika!")
        .setContentText(filePath)
        .setAutoCancel(true)
        .setContentIntent(pendingIntent)
        .build()
    NotificationManagerCompat.from(context)
        .notify(1001, notification)
}
