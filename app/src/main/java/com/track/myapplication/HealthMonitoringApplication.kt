package com.track.myapplication

import android.app.Application
import com.track.myapplication.data.AppContainer
import com.track.myapplication.data.DefaultAppContainer

class HealthMonitoringApplication : Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }


}

