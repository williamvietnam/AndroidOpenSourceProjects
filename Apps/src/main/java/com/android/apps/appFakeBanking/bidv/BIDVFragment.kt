package com.android.apps.appFakeBanking.bidv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.commons.base.BaseFragment
import com.android.databinding.FragmentBIDVBinding

class BIDVFragment : BaseFragment<FragmentBIDVBinding, BIDVViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBIDVBinding {
        return FragmentBIDVBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): BIDVViewModel {
        return ViewModelProvider(this)[BIDVViewModel::class.java]
    }

    override fun initializeViews() {

    }

    override fun initializeEvents() {

    }
}