package com.android.vncalling.data

import com.android.vncalling.data.database.DatabaseHelper
import com.android.vncalling.data.database.DatabaseImplement
import com.android.vncalling.data.preferences.PreferencesHelper
import com.android.vncalling.data.preferences.PreferencesImplement
import com.android.vncalling.data.remote.ApiClient
import com.android.vncalling.data.remote.ApiHelper
import com.android.vncalling.data.remote.ApiHelperImplement
import com.android.vncalling.data.remote.models.Model
import io.reactivex.rxjava3.core.Single

class DataManagerImplement(
    private val preferencesHelper: PreferencesHelper = PreferencesImplement(),
    private val databaseHelper: DatabaseHelper = DatabaseImplement(),
    private val apiHelper: ApiHelper = ApiHelperImplement(ApiClient.getApiService())
) : DataManager {
    //------------------------------------preferences local--------------------------------------
    fun getPreferencesHelper(): PreferencesHelper {
        return this.preferencesHelper
    }


    //-----------------------------------room database local--------------------------------------
    fun getDatabaseHelper(): DatabaseHelper {
        return this.databaseHelper
    }


    //-----------------------------------api remote------------------------------------------------
    fun getApiHelper(): ApiHelper {
        return this.apiHelper
    }

    override fun getModels(q: String): Single<List<Model>> = apiHelper.getModels(q)
}