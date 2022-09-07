package com.android.vncalling.ui.features.calling_screen

import androidx.lifecycle.ViewModelProvider
import com.android.vncalling.base.BaseActivity
import com.android.vncalling.databinding.ActivityIncomingCallBinding

/**
 * Cuoc goi den
 * */
class IncomingCallActivity : BaseActivity<ActivityIncomingCallBinding, CallingViewModel>() {
    override fun createViewModel(): CallingViewModel {
        return ViewModelProvider(this)[CallingViewModel::class.java]
    }

    override fun getViewBinding(): ActivityIncomingCallBinding {
        return ActivityIncomingCallBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        TODO("Not yet implemented")
    }
}