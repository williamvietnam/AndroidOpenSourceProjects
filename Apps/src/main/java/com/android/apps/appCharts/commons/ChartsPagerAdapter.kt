package com.android.apps.appCharts.commons

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.apps.appCharts.charts.BarChartFragment
import com.android.apps.appCharts.charts.PieChartFragment
import com.android.apps.appCharts.charts.RadarChartFragment

class ChartsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                PieChartFragment()
            }
            1 -> {
                BarChartFragment()
            }
            2 -> {
                RadarChartFragment()
            }
            else -> {
                PieChartFragment()
            }
        }
    }
}