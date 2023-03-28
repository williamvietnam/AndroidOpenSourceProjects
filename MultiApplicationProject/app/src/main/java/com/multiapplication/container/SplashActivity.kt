package com.multiapplication.container

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.multiapplication.base.BaseActivity
import com.multiapplication.databinding.ActivitySplashBinding

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
        val runnable = object : Runnable {
            override fun run() {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
        }
        handler.postDelayed(runnable, 1500)
    }
}