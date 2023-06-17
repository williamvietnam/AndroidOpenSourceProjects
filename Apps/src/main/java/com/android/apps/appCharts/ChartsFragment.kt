package com.android.apps.appCharts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.commons.base.BaseFragment
import com.android.databinding.FragmentChartsBinding

class ChartsFragment : BaseFragment<FragmentChartsBinding, ChartsViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChartsBinding {
        return FragmentChartsBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): ChartsViewModel {
        return ViewModelProvider(this)[ChartsViewModel::class.java]
    }

    override fun initializeViews() {

    }

    override fun initializeEvents() {

    }
}