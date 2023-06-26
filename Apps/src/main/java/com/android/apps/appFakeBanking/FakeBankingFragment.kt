package com.android.apps.appFakeBanking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.commons.base.BaseFragment
import com.android.databinding.FragmentFakeBankingBinding

class FakeBankingFragment : BaseFragment<FragmentFakeBankingBinding, FakeBankingViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFakeBankingBinding {
        return FragmentFakeBankingBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): FakeBankingViewModel {
        return ViewModelProvider(this)[FakeBankingViewModel::class.java]
    }

    override fun initializeViews() {

    }

    override fun initializeEvents() {

    }
}