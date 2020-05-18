package hr.ferit.kristijankoscak.gdjejekristijan

import android.app.Application
import android.content.Context

class WhereAmIApp :Application(){
    companion object {
        lateinit var ApplicationContext: Context
            private set
    }
    override fun onCreate() {
        super.onCreate()
        ApplicationContext = this
    }
}