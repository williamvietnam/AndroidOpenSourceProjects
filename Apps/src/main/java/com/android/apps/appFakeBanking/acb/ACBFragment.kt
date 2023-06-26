package com.android.apps.appFakeBanking.acb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.commons.base.BaseFragment
import com.android.databinding.FragmentACBBinding

class ACBFragment : BaseFragment<FragmentACBBinding, ACBViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentACBBinding {
        return FragmentACBBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): ACBViewModel {
        return ViewModelProvider(this)[ACBViewModel::class.java]
    }

    override fun initializeViews() {

    }

    override fun initializeEvents() {

    }
}