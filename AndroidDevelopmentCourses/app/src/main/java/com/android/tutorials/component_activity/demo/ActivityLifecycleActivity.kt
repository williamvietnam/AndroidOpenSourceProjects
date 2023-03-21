package com.android.tutorials.component_activity.demo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.databinding.ActivityActivityLifecycleBinding

class ActivityLifecycleActivity : AppCompatActivity() {

    companion object {
        const val TAG = "ActivityLifecycleActivity"
        const val ON_CREATE = "onCreate()..."
        const val ON_START = "onStart()..."
        const val ON_RESUME = "onResume()..."
        const val ON_PAUSE = "onCreate()..."
        const val ON_STOP = "onStop()..."
        const val ON_DESTROY = "onDestroy()..."
    }

    private lateinit var binding: ActivityActivityLifecycleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityActivityLifecycleBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        Log.d(TAG, ON_CREATE)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, ON_START)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, ON_RESUME)
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, ON_PAUSE)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, ON_STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, ON_DESTROY)
    }
}