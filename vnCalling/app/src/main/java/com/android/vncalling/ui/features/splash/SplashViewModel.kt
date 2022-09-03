package com.android.vncalling.ui.features.splash

import com.android.vncalling.base.BaseViewModel
import com.android.vncalling.utils.Constants

class SplashViewModel : BaseViewModel() {

    fun decideNextActivity(): String {
        return if (!getDataManger().isShowWelcomeScreen()) {
            Constants.WELCOME_ACTIVITY
        } else if (!getDataManger().isLogin()) {
            Constants.LOGIN_ACTIVITY
        } else {
            Constants.MAIN_ACTIVITY
        }
    }
}