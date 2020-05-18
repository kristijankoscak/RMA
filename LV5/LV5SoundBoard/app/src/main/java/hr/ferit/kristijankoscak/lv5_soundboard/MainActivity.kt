package hr.ferit.kristijankoscak.lv5_soundboard

import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity  : AppCompatActivity(), View.OnClickListener{
    private lateinit var soundHelper : SoundHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setUpUi()
    }
    private fun setUpUi() {
        this.mamic_btn.setOnClickListener(this)
        this.mark_btn.setOnClickListener(this)
        this.musk_btn.setOnClickListener(this)
        soundHelper = SoundHelper(this)
        soundHelper.loadSounds()
    }
    override fun onClick(v: View) {
        if (soundHelper.mLoaded == false) return
        when (v.getId()) {
            R.id.mamic_btn -> soundHelper.playSound(R.raw.mamic)
            R.id.musk_btn -> soundHelper.playSound(R.raw.glass_break)
            R.id.mark_btn -> soundHelper.playSound(R.raw.countoff)

        }
    }

}
