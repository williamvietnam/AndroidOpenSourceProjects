package com.android.apps.appCharts.charts

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.apps.appCharts.commons.ChartsViewModel
import com.android.commons.base.BaseFragment
import com.android.databinding.FragmentPieChartBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class PieChartFragment : BaseFragment<FragmentPieChartBinding, ChartsViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPieChartBinding {
        return FragmentPieChartBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): ChartsViewModel {
        return ViewModelProvider(this)[ChartsViewModel::class.java]
    }

    override fun initializeViews() {
        val visitors: MutableList<PieEntry> = ArrayList()
        visitors.add(PieEntry(508f, "2014"))
        visitors.add(PieEntry(600f, "2015"))
        visitors.add(PieEntry(608f, "2016"))
        visitors.add(PieEntry(550f, "2017"))
        visitors.add(PieEntry(500f, "2018"))
        visitors.add(PieEntry(560f, "2019"))
        visitors.add(PieEntry(620f, "2020"))
        visitors.add(PieEntry(480f, "2021"))

        val pieDataSet = PieDataSet(visitors, "Visitors")
        pieDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()
        pieDataSet.valueTextColor = Color.BLACK
        pieDataSet.valueTextSize = 16f

        val pieData = PieData(pieDataSet)
        binding.pieChart.data = pieData
        binding.pieChart.description.isEnabled = false
        binding.pieChart.centerText = "Visitors"
        binding.pieChart.animate()
    }

    override fun initializeEvents() {}
}