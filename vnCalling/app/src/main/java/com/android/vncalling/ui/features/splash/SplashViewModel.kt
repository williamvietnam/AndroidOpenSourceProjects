package com.android.vncalling.ui.features.splash

import com.android.vncalling.base.BaseViewModel
import com.android.vncalling.utilities.Constants

class SplashViewModel : BaseViewModel() {

    fun decideNextActivity(): String {
        if (!getDataManger().isShowWelcomeScreen()) {
            return Constants.WELCOME_ACTIVITY
        } else if (!getDataManger().isLogin()) {
            return Constants.LOGIN_ACTIVITY
        }
        return Constants.MAIN_ACTIVITY
    }
}