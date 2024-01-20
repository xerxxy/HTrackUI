package com.htrackk.myapplication

import android.app.Application
import com.htrackk.myapplication.data.AppContainer
import com.htrackk.myapplication.data.DefaultAppContainer

class HealthMonitoringApplication : Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }


}

