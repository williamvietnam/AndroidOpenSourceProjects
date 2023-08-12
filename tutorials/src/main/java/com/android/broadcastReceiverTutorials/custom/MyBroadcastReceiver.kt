package com.android.broadcastReceiverTutorials.custom

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MyBroadcastReceiver : BroadcastReceiver() {

    private val data = MutableLiveData<String>()
    val liveData: LiveData<String> = data

    companion object {
        const val CUSTOM_ACTION = "demo.broadcast.receiver"
        const val DATA_KEY = "data.test"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == CUSTOM_ACTION) {
            data.value = intent.getStringExtra(DATA_KEY)
        }
    }
}