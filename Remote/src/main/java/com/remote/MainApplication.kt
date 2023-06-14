package com.remote

import android.app.Application
import android.util.Log

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d(MainApplication::class.java.name, "onCreated()...")
    }
}