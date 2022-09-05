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
    override fun getPreferencesHelper(): PreferencesHelper {
        return this.preferencesHelper
    }

    override fun isShowWelcomeScreen(): Boolean {
        return this.preferencesHelper.isShowWelcomeScreen()
    }

    override fun setIsShowWelcomeScreen(isShow: Boolean) {
        this.preferencesHelper.setIsShowWelcomeScreen(isShow = isShow)
    }

    override fun isLogin(): Boolean {
        return this.preferencesHelper.isLogin()
    }

    override fun setIsLogin(isLogin: Boolean) {
        this.preferencesHelper.setIsLogin(isLogin = isLogin)
    }

    override fun setUserAvatar(userAvatar: String) {
        this.preferencesHelper.setUserAvatar(userAvatar = userAvatar)
    }

    override fun setUserName(userName: String) {
        this.preferencesHelper.setUserName(userName = userName)
    }

    override fun setUserAccount(accountName: String) {
        this.preferencesHelper.setUserAccount(accountName = accountName)
    }

    override fun getUserAvatar(): String? {
        return this.preferencesHelper.getUserAvatar()
    }

    override fun getUserName(): String? {
        return this.preferencesHelper.getUserName()
    }

    override fun getUserAccount(): String? {
        return this.preferencesHelper.getUserAccount()
    }


    //-----------------------------------room database local--------------------------------------
    override fun getDatabaseHelper(): DatabaseHelper {
        return this.databaseHelper
    }


    //-----------------------------------api remote------------------------------------------------
    override fun getApiHelper(): ApiHelper {
        return this.apiHelper
    }

    override fun getModels(q: String): Single<List<Model>> = apiHelper.getModels(q)
}