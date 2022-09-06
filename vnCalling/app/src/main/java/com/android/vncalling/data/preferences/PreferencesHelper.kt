package com.android.vncalling.data.preferences

interface PreferencesHelper {
    fun isShowWelcomeScreen(): Boolean

    fun putIsShowWelcomeScreen(isShow: Boolean)

    fun isLogin(): Boolean

    fun putIsLogin(isLogin: Boolean)

    //----------------------------- personal information ------------------------------------
    fun putUserId(userId: String)

    fun putUserAvatar(userAvatar: String?)

    fun putUserName(userName: String?)

    fun putUserAccount(accountName: String?)

    fun putUserPassword(password: String?)

    fun getUserId(): String?

    fun getUserAvatar(): String?

    fun getUserName(): String?

    fun getUserAccount(): String?

    fun getUserPassword(): String?
}