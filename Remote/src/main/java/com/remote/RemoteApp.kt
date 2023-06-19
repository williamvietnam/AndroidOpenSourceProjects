package com.remote

import android.app.Application
import android.util.Log
import com.connectsdk.discovery.DiscoveryManager
import com.connectsdk.service.DIALService

class RemoteApp : Application() {
    override fun onCreate() {
        DIALService.registerApp("Levak")
        DiscoveryManager.init(applicationContext)
        super.onCreate()
        Log.d(RemoteApp::class.java.name, "onCreated()...")
    }
}