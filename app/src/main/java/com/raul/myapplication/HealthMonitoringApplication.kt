package com.raul.myapplication

import android.app.Application
import android.content.Context
import com.raul.myapplication.data.AppContainer
import com.raul.myapplication.data.DefaultAppContainer
import com.raul.myapplication.data.remote.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileNotFoundException
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class HealthMonitoringApplication : Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }


}

