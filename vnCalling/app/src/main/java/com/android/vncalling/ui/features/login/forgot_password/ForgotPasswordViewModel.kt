package com.android.vncalling.ui.features.login.forgot_password

import com.android.vncalling.base.BaseViewModel

class ForgotPasswordViewModel : BaseViewModel() {

    fun isNullOrEmpty(accountName: String): Boolean {
        if (accountName.isEmpty()) {
            return true
        }
        return false
    }

    fun isValidateTextField(accountName: String): Boolean {
        //todor
        return true
    }
}