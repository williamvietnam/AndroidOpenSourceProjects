package com.android.vncalling.ui.features.login.signup

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.android.vncalling.base.BaseViewModel
import com.android.vncalling.utilities.Constants


class SignUpViewModel : BaseViewModel() {

    private val showMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    private val isRegisterSuccess: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun getShowToast(): MutableLiveData<String> {
        return this.showMessage
    }

    fun getIsLoading(): MutableLiveData<Boolean> {
        return this.isLoading
    }

    fun getIsRegisterSuccess(): MutableLiveData<Boolean> {
        return this.isRegisterSuccess
    }

    fun isValidateRegisterInformation(
        avatarEncodedImage: String?, userName: String,
        userAccount: String, password: String, confirmPassword: String
    ): Boolean {
        if (avatarEncodedImage == null) {
            showMessage.value = "Thêm ảnh hồ sơ"
            return false
        } else if (userName.isEmpty()) {
            showMessage.value = "Mời nhập Họ Tên"
            return false
        } else if (userAccount.isEmpty()) {
            showMessage.value = "Mời nhập Tài khoản"
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(userAccount).matches()) {
            showMessage.value = "Nhập đúng định dạng tài khoản"
            return false
        } else if (password.isEmpty()) {
            showMessage.value = "Nhập mật khẩu"
            return false
        } else if (confirmPassword.isEmpty()) {
            showMessage.value = "Nhập lại mật khẩu"
            return false
        } else if (password != confirmPassword) {
            showMessage.value = "Mật khẩu và nhập lại mật khẩu phải trùng nhau"
            return false
        }
        return true
    }

    fun register(avatar: String, userName: String, accountName: String, password: String) {
        isLoading.value = true
        val user: HashMap<String, Any> = HashMap()
        user[Constants.KEY_USER_AVATAR] = avatar
        user[Constants.KEY_USER_NAME] = userName
        user[Constants.KEY_USER_ACCOUNT] = accountName
        user[Constants.KEY_USER_PASSWORD] = password
        getFirebaseFirestore().collection(Constants.KEY_COLLECTION_USERS)
            .add(user)
            .addOnSuccessListener {
                isLoading.value = false
                getDataManger().putUserAvatar(userAvatar = avatar)
                getDataManger().putUserName(userName = userName)
                getDataManger().putUserAccount(accountName = accountName)
                getDataManger().putUserPassword(password = password)
                isRegisterSuccess.value = true
            }
            .addOnFailureListener { exception: Exception ->
                run {
                    isLoading.value = false
                    showMessage.value = exception.message
                    isRegisterSuccess.value = false
                }
            }
    }
}