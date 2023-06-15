package com.android

import android.app.Application
import android.util.Log
import com.android.commons.utilities.Preferences

class AppsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Preferences.instance.load(this)
        Log.d(AppsApplication::class.java.name, "onCreate()...")
    }
}