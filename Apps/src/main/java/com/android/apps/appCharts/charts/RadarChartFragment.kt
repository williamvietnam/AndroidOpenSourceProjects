package com.android.apps.appCharts.charts

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.apps.appCharts.commons.ChartsViewModel
import com.android.commons.base.BaseFragment
import com.android.databinding.FragmentRadarChartBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class RadarChartFragment : BaseFragment<FragmentRadarChartBinding, ChartsViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentRadarChartBinding {
        return FragmentRadarChartBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): ChartsViewModel {
        return ViewModelProvider(this)[ChartsViewModel::class.java]
    }

    override fun initializeViews() {
        val visitorsForFirstWebsite: MutableList<RadarEntry> = ArrayList()
        visitorsForFirstWebsite.add(RadarEntry(508f))
        visitorsForFirstWebsite.add(RadarEntry(600f))
        visitorsForFirstWebsite.add(RadarEntry(608f))
        visitorsForFirstWebsite.add(RadarEntry(550f))
        visitorsForFirstWebsite.add(RadarEntry(500f))
        visitorsForFirstWebsite.add(RadarEntry(560f))
        visitorsForFirstWebsite.add(RadarEntry(620f))
        visitorsForFirstWebsite.add(RadarEntry(480f))

        val radarDataSet = RadarDataSet(visitorsForFirstWebsite, "Website")
        radarDataSet.color = Color.RED
        radarDataSet.lineWidth = 2f
        radarDataSet.valueTextColor = Color.RED
        radarDataSet.valueTextSize = 14f

        val radarData = RadarData()
        radarData.addDataSet(radarDataSet)
        val labels = arrayOf("2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021")
        val xAxis: XAxis = binding.radarChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        binding.radarChart.data = radarData
    }

    override fun initializeEvents() {}
}