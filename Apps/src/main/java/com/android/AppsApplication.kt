package com.android

import android.app.Application
import android.util.Log

class AppsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d(AppsApplication::class.java.name, "onCreate()...")
    }
}