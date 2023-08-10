package com.android.broadcastReceiver.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class NetworkBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        @Suppress("DEPRECATION")
        if (intent?.action == ConnectivityManager.CONNECTIVITY_ACTION) {
            if (isNetworkAvailable()) {
                Toast.makeText(context, "Network Available", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Network Unavailable", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isNetworkAvailable(): Boolean {
        var isNeworktAvailable = false
        // todo( do check network this here)
        return isNeworktAvailable
    }
}