package com.android.services

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class ServicesTutorialsActivity : AppCompatActivity() {
    companion object {
        const val TAG = "ServicesTutorialsActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: $TAG")
    }
}