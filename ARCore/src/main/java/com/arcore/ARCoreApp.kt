package com.arcore

import android.app.Application
import android.util.Log

class ARCoreApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d(ARCoreApp::class.java.name, "onCreated()...")
    }
}