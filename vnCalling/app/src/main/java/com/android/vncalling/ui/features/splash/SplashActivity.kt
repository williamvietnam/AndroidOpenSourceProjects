package com.android.vncalling.ui.features.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.android.vncalling.base.BaseActivity
import com.android.vncalling.databinding.ActivitySplashBinding
import com.android.vncalling.ui.features.container.MainActivity
import com.android.vncalling.ui.features.login.LoginActivity
import com.android.vncalling.ui.features.welcome.WelcomeActivity
import com.android.vncalling.utils.Constants

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    private val handler: Handler = Handler(Looper.getMainLooper())
    private val runnable: Runnable = Runnable {
        when (viewModel.decideNextActivity()) {
            Constants.WELCOME_ACTIVITY -> {
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            Constants.LOGIN_ACTIVITY -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            Constants.MAIN_ACTIVITY -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun createViewModel(): SplashViewModel {
        return ViewModelProvider(this)[SplashViewModel::class.java]
    }

    override fun getViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        this.handler.postDelayed(runnable, 1200)
    }
}