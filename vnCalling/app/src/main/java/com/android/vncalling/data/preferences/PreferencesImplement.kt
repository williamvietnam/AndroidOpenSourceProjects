package com.android.vncalling.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.android.vncalling.MyApp

class PreferencesImplement(
    context: Context = MyApp.appContext!!
) : PreferencesHelper {

    companion object {

        const val KEY_PREFERENCE_NAME = "preference_name"

        const val KEY_PREFERENCE_IS_LOGIN = "is_login"

        const val KEY_PREFERENCE_IS_SHOW_WELCOME_SCREEN = "is_show_welcome_screen"

        const val KEY_PREF_USER_AVATAR = "pref_user_avatar"

        const val KEY_PREF_USER_NAME = "pref_user_name"

        const val KEY_PREF_USER_ACCOUNT = "pref_user_account"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(KEY_PREFERENCE_NAME, Context.MODE_PRIVATE)

    private val editor: SharedPreferences.Editor = this.sharedPreferences.edit()

    override fun isShowWelcomeScreen(): Boolean {
        return this.sharedPreferences.getBoolean(KEY_PREFERENCE_IS_SHOW_WELCOME_SCREEN, false)
    }

    override fun setIsShowWelcomeScreen(isShow: Boolean) {
        this.editor.putBoolean(KEY_PREFERENCE_IS_SHOW_WELCOME_SCREEN, isShow)
            .apply()
    }

    override fun isLogin(): Boolean {
        return this.sharedPreferences.getBoolean(KEY_PREFERENCE_IS_LOGIN, false)
    }

    override fun setIsLogin(isLogin: Boolean) {
        this.editor.putBoolean(KEY_PREFERENCE_IS_LOGIN, isLogin)
            .apply()
    }

    override fun setUserAvatar(userAvatar: String) {
        this.editor.putString(KEY_PREF_USER_AVATAR, userAvatar)
            .apply()
    }

    override fun setUserName(userName: String) {
        this.editor.putString(KEY_PREF_USER_NAME, userName)
            .apply()
    }

    override fun setUserAccount(accountName: String) {
        this.editor.putString(KEY_PREF_USER_ACCOUNT, accountName)
            .apply()
    }

    override fun getUserAvatar(): String? {
        return this.sharedPreferences.getString(KEY_PREF_USER_AVATAR, "")
    }

    override fun getUserName(): String? {
        return this.sharedPreferences.getString(KEY_PREF_USER_NAME, "")
    }

    override fun getUserAccount(): String? {
        return this.sharedPreferences.getString(KEY_PREF_USER_ACCOUNT, "")
    }
}