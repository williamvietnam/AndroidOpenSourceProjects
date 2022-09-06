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

        const val KEY_USER_ID = "userId"

        const val KEY_PREF_USER_AVATAR = "pref_user_avatar"

        const val KEY_PREF_USER_NAME = "pref_user_name"

        const val KEY_PREF_USER_ACCOUNT = "pref_user_account"

        const val KEY_PREF_USER_PASSWORD = "pref_user_password"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(KEY_PREFERENCE_NAME, Context.MODE_PRIVATE)

    private val editor: SharedPreferences.Editor = this.sharedPreferences.edit()

    override fun isShowWelcomeScreen(): Boolean {
        return this.sharedPreferences.getBoolean(KEY_PREFERENCE_IS_SHOW_WELCOME_SCREEN, false)
    }

    override fun putIsShowWelcomeScreen(isShow: Boolean) {
        this.editor.putBoolean(KEY_PREFERENCE_IS_SHOW_WELCOME_SCREEN, isShow)
            .apply()
    }

    override fun isLogin(): Boolean {
        return this.sharedPreferences.getBoolean(KEY_PREFERENCE_IS_LOGIN, false)
    }

    override fun putIsLogin(isLogin: Boolean) {
        this.editor.putBoolean(KEY_PREFERENCE_IS_LOGIN, isLogin)
            .apply()
    }

    override fun putUserId(userId: String) {
        this.editor.putString(KEY_USER_ID, userId)
    }

    override fun putUserAvatar(userAvatar: String?) {
        this.editor.putString(KEY_PREF_USER_AVATAR, userAvatar)
            .apply()
    }

    override fun putUserName(userName: String?) {
        this.editor.putString(KEY_PREF_USER_NAME, userName)
            .apply()
    }

    override fun putUserAccount(accountName: String?) {
        this.editor.putString(KEY_PREF_USER_ACCOUNT, accountName)
            .apply()
    }

    override fun putUserPassword(password: String?) {
        this.editor.putString(KEY_PREF_USER_PASSWORD, password)
            .apply()
    }

    override fun getUserId(): String? {
        return this.sharedPreferences.getString(KEY_USER_ID, "")
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

    override fun getUserPassword(): String? {
        return this.sharedPreferences.getString(KEY_PREF_USER_PASSWORD, "")
    }
}