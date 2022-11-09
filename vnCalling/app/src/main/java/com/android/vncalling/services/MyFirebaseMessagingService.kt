package com.android.vncalling.services

import android.app.ActivityManager
import android.content.Context
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "Token: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if (message.notification != null) {
            Log.d("FCM", "Message: " + message.notification?.body)
        }
        val myProcess = ActivityManager.RunningAppProcessInfo()
        ActivityManager.getMyMemoryState(myProcess)
        showNotification(this, message)
    }

    private fun showNotification(context: Context, remoteMessage: RemoteMessage) {

    }

}