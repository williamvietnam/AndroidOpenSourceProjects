package com.android.container

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.android.base.BaseActivity
import com.android.databinding.ActivitySplashBinding
import com.multiapplication.container.SplashViewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override fun createViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): SplashViewModel {
        return ViewModelProvider(this)[SplashViewModel::class.java]
    }

    override fun initializeView() {
        val handler = Handler(Looper.getMainLooper())
        val runnable = Runnable {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
        handler.postDelayed(runnable, 1500)
    }
}