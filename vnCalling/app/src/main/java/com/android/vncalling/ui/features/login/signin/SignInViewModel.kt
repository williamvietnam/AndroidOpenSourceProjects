package com.android.vncalling.ui.features.login.signin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.vncalling.base.BaseViewModel
import com.android.vncalling.utilities.Constants
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot


class SignInViewModel : BaseViewModel() {

    private val showMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    private val isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    private val isLoginSuccess: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun getShowToast(): MutableLiveData<String> {
        return this.showMessage
    }

    fun getIsLoading(): MutableLiveData<Boolean> {
        return this.isLoading
    }

    fun getIsLoginSuccess(): MutableLiveData<Boolean> {
        return this.isLoginSuccess
    }

    fun isValidateInputFields(accountName: String, password: String): Boolean {
        if (accountName.isEmpty()) {
            showMessage.value = "Please, typing account of you to login"
            return false
        } else if (password.isEmpty()) {
            showMessage.value = "Please, typing password of you to login"
            return false
        }
        return true
    }

    fun login(accountName: String, password: String) {
        this.isLoading.value = true
        getFirebaseFirestore().collection(Constants.KEY_COLLECTION_USERS)
            .whereEqualTo(Constants.KEY_USER_ACCOUNT, accountName)
            .whereEqualTo(Constants.KEY_USER_PASSWORD, password)
            .get()
            .addOnCompleteListener(object : OnCompleteListener<QuerySnapshot?> {
                override fun onComplete(task: Task<QuerySnapshot?>) {
                    if (task.isSuccessful && task.result != null && task.result!!.documents.size > 0) {
                        val documentSnapshot = task.result!!.documents[0]
                        getDataManger().putIsLogin(true)
                        getDataManger().putUserId(documentSnapshot.id)
                        getDataManger().putUserAvatar(documentSnapshot.getString(Constants.KEY_USER_AVATAR))
                        getDataManger().putUserName(documentSnapshot.getString(Constants.KEY_USER_NAME))
                        getDataManger().putUserAccount(documentSnapshot.getString(Constants.KEY_USER_ACCOUNT))
                        Log.d(SignInFragment::class.simpleName, "debug: login success")
                        isLoginSuccess.value = true
                    } else {
                        isLoading.value = false
                        showMessage.value = "Can not login!"
                        isLoginSuccess.value = false
                    }
                }
            })
    }
}