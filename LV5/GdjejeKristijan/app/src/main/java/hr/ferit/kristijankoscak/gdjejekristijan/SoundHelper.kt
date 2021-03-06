package hr.ferit.kristijankoscak.gdjejekristijan

import android.content.Context
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build

class SoundHelper(val context:Context) {
    private lateinit var mSoundPool: SoundPool
    private var mLoaded: Boolean = false
    var mSoundMap: HashMap<Int, Int> = HashMap()

    fun loadSounds() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.mSoundPool = SoundPool.Builder().setMaxStreams(10).build()
        } else {
            this.mSoundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
        }
        this.mSoundPool.setOnLoadCompleteListener { _, _, _ -> mLoaded = true }
        this.mSoundMap[R.raw.mariojump] = this.mSoundPool.load(context, R.raw.mariojump, 1)
    }
    fun playSound() {
        val soundID = this.mSoundMap[R.raw.mariojump] ?: 0
        this.mSoundPool.play(soundID, 1f, 1f, 1, 0, 1f)
    }

}