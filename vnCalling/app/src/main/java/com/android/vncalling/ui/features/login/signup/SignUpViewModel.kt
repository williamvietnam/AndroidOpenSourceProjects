package com.android.vncalling.ui.features.login.signup

import com.android.vncalling.base.BaseViewModel

class SignUpViewModel : BaseViewModel() {

    fun isRegisterSuccess(
        avatar: String,
        userName: String,
        accountName: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (isValidTextFields(avatar, userName, accountName, password, confirmPassword)) {
            return true
        }
        return false
    }

    private fun isValidTextFields(
        avatar: String,
        userName: String,
        accountName: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        //todo
        return false
    }
}