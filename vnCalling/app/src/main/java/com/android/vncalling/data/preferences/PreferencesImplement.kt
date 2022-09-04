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

    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(KEY_PREFERENCE_NAME, Context.MODE_PRIVATE)

    private val editor: SharedPreferences.Editor = this.sharedPreferences.edit()

    override fun isLogin(): Boolean {
        return this.sharedPreferences.getBoolean(KEY_PREFERENCE_IS_LOGIN, false)
    }

    override fun setIsLogin(isLogin: Boolean) {
        this.editor.putBoolean(KEY_PREFERENCE_IS_LOGIN, isLogin)
        this.editor.apply()
    }

    override fun isShowWelcomeScreen(): Boolean {
        return this.sharedPreferences.getBoolean(KEY_PREFERENCE_IS_SHOW_WELCOME_SCREEN, false)
    }

    override fun setIsShowWelcomeScreen(isShow: Boolean) {
        this.editor.putBoolean(KEY_PREFERENCE_IS_SHOW_WELCOME_SCREEN, isShow)
            .apply()
    }
}