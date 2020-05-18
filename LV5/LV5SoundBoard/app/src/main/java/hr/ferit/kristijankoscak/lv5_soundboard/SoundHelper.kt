package hr.ferit.kristijankoscak.lv5_soundboard

import android.content.Context
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build

class SoundHelper(val context:Context) {
    private lateinit var mSoundPool: SoundPool
    var mLoaded: Boolean = false
    private var mSoundMap: HashMap<Int, Int> = HashMap()

    fun loadSounds() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.mSoundPool = SoundPool.Builder().setMaxStreams(10).build()
        } else {
            this.mSoundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
        }
        this.mSoundPool.setOnLoadCompleteListener { _, _, _ -> mLoaded = true }
        this.mSoundMap[R.raw.mamic] = this.mSoundPool.load(context, R.raw.mamic, 1)
        this.mSoundMap[R.raw.countoff] = this.mSoundPool.load(context, R.raw.countoff, 1)
        this.mSoundMap[R.raw.glass_break] = this.mSoundPool.load(context, R.raw.glass_break, 1)
    }
    fun playSound(selectedSound: Int) {
        val soundID = this.mSoundMap[selectedSound] ?: 0
        this.mSoundPool.play(soundID, 1f, 1f, 1, 0, 1f)
    }
}