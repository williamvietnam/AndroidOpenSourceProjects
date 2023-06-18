package com.android.apps.appCharts.charts

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.apps.appCharts.commons.ChartsViewModel
import com.android.commons.base.BaseFragment
import com.android.databinding.FragmentBarChartBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate

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
        val visitors: MutableList<BarEntry> = ArrayList()
        visitors.add(BarEntry(2014f, 660f))
        visitors.add(BarEntry(2015f, 505f))
        visitors.add(BarEntry(2016f, 508f))
        visitors.add(BarEntry(2017f, 580f))
        visitors.add(BarEntry(2018f, 505f))
        visitors.add(BarEntry(2019f, 680f))
        visitors.add(BarEntry(2020f, 630f))
        visitors.add(BarEntry(2021f, 520f))

        val barDataSet = BarDataSet(visitors, "Visitors")
        barDataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 16f

        val barData = BarData(barDataSet)
        binding.barChart.setFitBars(true)
        binding.barChart.data = barData
        binding.barChart.description.text = "Bar Chart"
        binding.barChart.animateY(2000)
    }

    override fun initializeEvents() {}
}