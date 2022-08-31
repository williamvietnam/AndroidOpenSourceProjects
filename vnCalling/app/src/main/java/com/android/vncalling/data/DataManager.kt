package com.android.vncalling.data

import com.android.vncalling.data.database.DatabaseHelper
import com.android.vncalling.data.preferences.PreferencesHelper
import com.android.vncalling.data.remote.ApiHelper

interface DataManager : PreferencesHelper, DatabaseHelper, ApiHelper {

}