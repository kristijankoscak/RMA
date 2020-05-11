package hr.ferit.kristijankoscak.inspiringpersondb

import android.app.Application
import android.content.Context

class ContextSaver : Application() {
    companion object {
        lateinit var ApplicationContext: Context
            private set
    }
    override fun onCreate() {
        super.onCreate()
        ApplicationContext = this
    }
}
