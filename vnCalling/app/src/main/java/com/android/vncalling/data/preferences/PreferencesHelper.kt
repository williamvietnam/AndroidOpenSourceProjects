package com.android.vncalling.data.preferences

interface PreferencesHelper {
    fun isLogin(): Boolean

    fun setIsLogin(isLogin: Boolean)

    fun isShowWelcomeScreen(): Boolean

    fun setIsShowWelcomeScreen(isShow: Boolean)
}