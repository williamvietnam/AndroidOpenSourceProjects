package com.google.machine_learning_kit

import android.app.Application
import android.util.Log

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d(MyApp::class.java.name, "onCreate()...")
    }
}