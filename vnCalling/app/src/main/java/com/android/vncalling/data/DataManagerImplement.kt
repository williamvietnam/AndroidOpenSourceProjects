package com.android.vncalling.data

import com.android.vncalling.data.database.DatabaseHelper
import com.android.vncalling.data.database.DatabaseImplement
import com.android.vncalling.data.preferences.PreferencesHelper
import com.android.vncalling.data.preferences.PreferencesImplement
import com.android.vncalling.data.remote.ApiClient
import com.android.vncalling.data.remote.ApiHelper
import com.android.vncalling.data.remote.ApiHelperImplement
import com.android.vncalling.data.remote.firebase.FirebaseServicesHelper
import com.android.vncalling.data.remote.firebase.FirebaseServicesImplement
import com.android.vncalling.data.remote.models.Model
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Single

class DataManagerImplement(
    private val preferencesHelper: PreferencesHelper = PreferencesImplement(),
    private val databaseHelper: DatabaseHelper = DatabaseImplement(),
    private val apiHelper: ApiHelper = ApiHelperImplement(ApiClient.getApiService()),
    private val firebaseServices: FirebaseServicesHelper = FirebaseServicesImplement()
) : DataManager {
    //------------------------------------preferences local--------------------------------------
    override fun getPreferencesHelper(): PreferencesHelper {
        return this.preferencesHelper
    }

    override fun isShowWelcomeScreen(): Boolean {
        return this.preferencesHelper.isShowWelcomeScreen()
    }

    override fun putIsShowWelcomeScreen(isShow: Boolean) {
        this.preferencesHelper.putIsShowWelcomeScreen(isShow = isShow)
    }

    override fun isLogin(): Boolean {
        return this.preferencesHelper.isLogin()
    }

    override fun putIsLogin(isLogin: Boolean) {
        this.preferencesHelper.putIsLogin(isLogin = isLogin)
    }

    override fun putUserId(userId: String) {
        this.preferencesHelper.putUserId(userId = userId)
    }

    override fun putUserAvatar(userAvatar: String?) {
        this.preferencesHelper.putUserAvatar(userAvatar = userAvatar)
    }

    override fun putUserName(userName: String?) {
        this.preferencesHelper.putUserName(userName = userName)
    }

    override fun putUserAccount(accountName: String?) {
        this.preferencesHelper.putUserAccount(accountName = accountName)
    }

    override fun putUserPassword(password: String?) {
        this.preferencesHelper.putUserPassword(password = password)
    }

    override fun getUserId(): String? {
        return this.preferencesHelper.getUserId()
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

    override fun getUserPassword(): String? {
        return this.preferencesHelper.getUserPassword()
    }


    //-----------------------------------room database local------------------------------------
    override fun getDatabaseHelper(): DatabaseHelper {
        return this.databaseHelper
    }


    //-----------------------------------api remote----------------------------------------------
    override fun getApiHelper(): ApiHelper {
        return this.apiHelper
    }

    override fun getModels(q: String): Single<List<Model>> = apiHelper.getModels(q)

    //------------------------------ firebase services -----------------------------------------
    override fun getFirebaseServices(): FirebaseServicesHelper {
        return this.firebaseServices
    }

    override fun getFirebaseFireStore(): FirebaseFirestore {
        return this.firebaseServices.getFirebaseFireStore()
    }
}