package com.android.apps.appCharts.charts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.apps.appCharts.commons.ChartsViewModel
import com.android.commons.base.BaseFragment
import com.android.databinding.FragmentBarChartBinding

class BarChartFragment : BaseFragment<FragmentBarChartBinding, ChartsViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBarChartBinding {
        return FragmentBarChartBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): ChartsViewModel {
        return ViewModelProvider(this)[ChartsViewModel::class.java]
    }

    override fun initializeViews() {

    }

    override fun initializeEvents() {

    }
}