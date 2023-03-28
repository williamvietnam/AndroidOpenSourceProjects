package com.multiapplication.apps.media.ui.main

import androidx.lifecycle.ViewModelProvider
import com.multiapplication.base.BaseActivity
import com.multiapplication.databinding.ActivityMediaBinding

class MediaActivity : BaseActivity<ActivityMediaBinding, MediaViewModel>() {
    override fun createViewBinding(): ActivityMediaBinding {
        return ActivityMediaBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): MediaViewModel {
        return ViewModelProvider(this)[MediaViewModel::class.java]
    }

    override fun initializeView() {
        TODO("Not yet implemented")
    }
}