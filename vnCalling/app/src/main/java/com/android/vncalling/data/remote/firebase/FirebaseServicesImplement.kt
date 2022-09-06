package com.android.vncalling.data.remote.firebase

import com.google.firebase.firestore.FirebaseFirestore

class FirebaseServicesImplement(
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) : FirebaseServicesHelper {

    override fun getFirebaseFireStore(): FirebaseFirestore {
        return this.firebaseFirestore
    }
}