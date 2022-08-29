package com.android.vncalling.ui.features.splash

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import com.android.vncalling.base.BaseActivity
import com.android.vncalling.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override fun createViewModel(): SplashViewModel {
        return ViewModelProvider(this)[SplashViewModel::class.java]
    }

    override fun getViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        TODO("Not yet implemented")
    }
}