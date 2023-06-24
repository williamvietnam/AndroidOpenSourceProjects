package com.android.apps.appStepCounter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.commons.base.BaseFragment
import com.android.databinding.FragmentStepCounterBinding

class StepCounterFragment : BaseFragment<FragmentStepCounterBinding, StepCounterViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStepCounterBinding {
        return FragmentStepCounterBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): StepCounterViewModel {
        return ViewModelProvider(this)[StepCounterViewModel::class.java]
    }

    override fun initializeViews() {

    }

    override fun initializeEvents() {

    }
}