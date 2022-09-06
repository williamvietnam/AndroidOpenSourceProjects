package com.android.vncalling.data

import com.android.vncalling.data.database.DatabaseHelper
import com.android.vncalling.data.preferences.PreferencesHelper
import com.android.vncalling.data.remote.ApiHelper
import com.android.vncalling.data.remote.firebase.FirebaseServicesHelper

interface DataManager : PreferencesHelper, DatabaseHelper, ApiHelper, FirebaseServicesHelper {
    fun getPreferencesHelper(): PreferencesHelper

    fun getDatabaseHelper(): DatabaseHelper

    fun getApiHelper(): ApiHelper

    fun getFirebaseServices(): FirebaseServicesHelper
}