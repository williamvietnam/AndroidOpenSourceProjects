package com.android.container.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.android.base.BaseActivity
import com.android.container.MainActivity
import com.android.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override fun createViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): SplashViewModel {
        return ViewModelProvider(this)[SplashViewModel::class.java]
    }

    override fun initializeViews() {
        val handler = Handler(Looper.getMainLooper())
        val runnable = Runnable {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
        handler.postDelayed(runnable, 1500)
    }

    override fun initializeEvents() {}
}