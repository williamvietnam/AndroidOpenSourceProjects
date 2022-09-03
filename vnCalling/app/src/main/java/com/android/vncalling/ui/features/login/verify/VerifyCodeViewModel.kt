package com.android.vncalling.ui.features.login.verify

import com.android.vncalling.base.BaseViewModel

class VerifyCodeViewModel : BaseViewModel() {

    fun isVerifyCodeSuccess(
        firstInput: String, secondInput: String, thirdInput: String, fourthInput: String
    ): Boolean {
        if (firstInput == "1" && secondInput == "1" && thirdInput == "1" && fourthInput == "1") {
            return true
        }
        return false
    }
}