package com.android.vncalling.ui.features.welcome

import androidx.lifecycle.ViewModelProvider
import com.android.vncalling.base.BaseActivity
import com.android.vncalling.databinding.ActivityWelcomeBinding

class WelcomeActivity : BaseActivity<ActivityWelcomeBinding, WelcomeViewModel>() {
    override fun createViewModel(): WelcomeViewModel =
        ViewModelProvider(this)[WelcomeViewModel::class.java]

    override fun getViewBinding(): ActivityWelcomeBinding =
        ActivityWelcomeBinding.inflate(layoutInflater)

    override fun initialize() {
        TODO("Not yet implemented")
    }
}