package hr.ferit.kristijankoscak.birdcounter
import android.content.Context

class PreferenceManager {
    companion object {
        const val PREFS_FILE = "MyPreferences"
        const val PREFS_KEY_COUNTER  = "counter"
        const val PREFS_KEY_COLOR  = "color"
    }
    private val sharedPreferences = ApplicationContextBuilder.ApplicationContext.getSharedPreferences(
        PREFS_FILE, Context.MODE_PRIVATE
    )
    private val editor = sharedPreferences.edit()
    fun increaseCounter() {
        val tempValue = sharedPreferences.getInt(PREFS_KEY_COUNTER,0)
        editor.putInt(PREFS_KEY_COUNTER,tempValue+1)
        editor.commit()
    }
    fun setColor(value:Int) {
        editor.putInt(PREFS_KEY_COLOR,value)
        editor.commit()
    }
    fun retrieveCounter(): Int {
        return sharedPreferences.getInt(PREFS_KEY_COUNTER,0)
    }
    fun retrieveColor(): Int {
        return sharedPreferences.getInt(PREFS_KEY_COLOR,0)
    }
    fun resetCounter(){
        editor.putInt(PREFS_KEY_COUNTER,0)
        editor.apply()
    }
    fun resetColor(){
        editor.putInt(PREFS_KEY_COLOR,0)
        editor.apply()
    }
}