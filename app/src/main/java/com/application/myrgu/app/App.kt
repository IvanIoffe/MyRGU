package com.application.myrgu.app

import android.app.Application
import com.application.myrgu.core.di.AppComponent
import com.application.myrgu.core.di.DaggerAppComponent

class App: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .context(this)
            .build()
    }
}