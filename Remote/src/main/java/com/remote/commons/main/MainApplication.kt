package com.remote.commons.main

import android.app.Application
import android.util.Log
import com.connectsdk.discovery.DiscoveryManager
import com.connectsdk.service.DIALService

class MainApplication : Application() {
    override fun onCreate() {
        DIALService.registerApp("Levak")
        DiscoveryManager.init(applicationContext)
        super.onCreate()
        Log.d(MainApplication::class.java.name, "onCreated()...")
    }
}