package com.android.vncalling.ui.features.calling_screen

import androidx.lifecycle.ViewModelProvider
import com.android.vncalling.base.BaseActivity
import com.android.vncalling.databinding.ActivityOutgoingCallBinding

/**
 * Cuoc goi di
 * */
class OutgoingCallActivity : BaseActivity<ActivityOutgoingCallBinding, CallingViewModel>() {
    override fun createViewModel(): CallingViewModel {
        return ViewModelProvider(this)[CallingViewModel::class.java]
    }

    override fun getViewBinding(): ActivityOutgoingCallBinding {
        return ActivityOutgoingCallBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        TODO("Not yet implemented")
    }
}