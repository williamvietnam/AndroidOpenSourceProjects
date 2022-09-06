package com.android.vncalling.data.remote.firebase

import com.google.firebase.firestore.FirebaseFirestore

interface FirebaseServicesHelper {
    fun getFirebaseFireStore(): FirebaseFirestore
}