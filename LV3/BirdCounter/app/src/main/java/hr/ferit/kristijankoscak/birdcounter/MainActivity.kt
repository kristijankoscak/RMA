package hr.ferit.kristijankoscak.birdcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var preferenceManager:PreferenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferenceManager = PreferenceManager()
        setUpUi()
    }
    private fun setUpUi() {
        btnRed.setOnClickListener{handleColorButtonClick(R.color.btnRed)}
        btnGreen.setOnClickListener{handleColorButtonClick(R.color.btnGreen)}
        btnBlue.setOnClickListener{handleColorButtonClick(R.color.btnBlue)}
        btnYellow.setOnClickListener{handleColorButtonClick(R.color.btnYellow)}
        btnResetCounter.setOnClickListener{resetCounter()}
        btnResetColor.setOnClickListener{resetColor()}
    }
    override fun onResume() {
        super.onResume()
        displayUI()
    }
    private fun displayUI() {
        val counter = PreferenceManager().retrieveCounter()
        tvCounter.text = counter.toString()
        tvCounter.setBackgroundResource(PreferenceManager().retrieveColor())
    }
    private fun handleColorButtonClick(value:Int) {
        preferenceManager.increaseCounter()
        preferenceManager.setColor(value)
        displayUI()
    }
    private fun resetCounter() {
        preferenceManager.resetCounter()
        displayUI()
    }
    private fun resetColor(){
        preferenceManager.resetColor()
        displayUI()
    }
}
