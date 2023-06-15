package com.remote.brands.sony.features

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.remote.commons.base.BaseFragment
import com.remote.databinding.FragmentSonyTvRemoteControlBinding

class SonyTVRemoteControlFragment :
    BaseFragment<FragmentSonyTvRemoteControlBinding, SonyTVRemoteControlViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSonyTvRemoteControlBinding {
        return FragmentSonyTvRemoteControlBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): SonyTVRemoteControlViewModel {
        return ViewModelProvider(this)[SonyTVRemoteControlViewModel::class.java]
    }

    override fun initializeViews() {

    }

    override fun initializeEvents() {

    }
}