package com.android

import android.app.Application
import android.util.Log

class MachineLearningApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d(MachineLearningApp::class.java.name, "onCreate()...")
    }
}