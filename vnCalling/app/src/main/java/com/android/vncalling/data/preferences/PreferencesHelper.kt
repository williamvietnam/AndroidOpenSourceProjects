package com.android.vncalling.data.preferences

interface PreferencesHelper {
    fun isShowWelcomeScreen(): Boolean

    fun setIsShowWelcomeScreen(isShow: Boolean)

    fun isLogin(): Boolean

    fun setIsLogin(isLogin: Boolean)

    //----------------------------- personal information ------------------------------------
    fun setUserAvatar(userAvatar: String)

    fun setUserName(userName: String)

    fun setUserAccount(accountName: String)

    fun getUserAvatar(): String?

    fun getUserName(): String?

    fun getUserAccount(): String?
}