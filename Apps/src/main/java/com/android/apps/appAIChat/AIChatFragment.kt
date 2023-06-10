package com.android.apps.appAIChat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.core.base.BaseFragment
import com.android.databinding.FragmentAIChatBinding

class AIChatFragment : BaseFragment<FragmentAIChatBinding, AIChatViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAIChatBinding {
        return FragmentAIChatBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): AIChatViewModel {
        return ViewModelProvider(this)[AIChatViewModel::class.java]
    }

    override fun initializeViews() {
    }

    override fun initializeEvents() {
    }
}