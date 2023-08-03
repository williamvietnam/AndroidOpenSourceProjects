package com.data.storages

import android.app.Application
import android.util.Log

class DataStoragesApp : Application() {
    companion object {
        const val TAG = "DataStoragesApp"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }
}