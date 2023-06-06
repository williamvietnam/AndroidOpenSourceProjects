package com.android.apps.appFakeCall.features.incomingCall

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.base.BaseFragment
import com.android.databinding.FragmentIncomingCallBinding

class IncomingCallFragment : BaseFragment<FragmentIncomingCallBinding, IncomingCallViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentIncomingCallBinding {
        return FragmentIncomingCallBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): IncomingCallViewModel {
        return ViewModelProvider(this)[IncomingCallViewModel::class.java]
    }

    override fun initializeViews() {
        TODO("Not yet implemented")
    }

    override fun initializeEvents() {
        TODO("Not yet implemented")
    }
}