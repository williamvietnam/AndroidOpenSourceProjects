package com.android.vncalling.ui.features.login

import androidx.lifecycle.ViewModelProvider
import com.android.vncalling.base.BaseActivity
import com.android.vncalling.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginView {

    companion object {
        private lateinit var activity: LoginActivity

        fun getInstance(): LoginActivity {
            return activity
        }
    }

    override fun createViewModel(): LoginViewModel {
        return ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun getViewBinding(): ActivityLoginBinding {
        activity = LoginActivity()
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun initialize() {
    }
}