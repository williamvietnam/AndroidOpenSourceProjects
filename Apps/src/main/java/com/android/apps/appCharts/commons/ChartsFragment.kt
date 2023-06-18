package com.android.apps.appCharts.commons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.commons.base.BaseFragment
import com.android.databinding.FragmentChartsBinding
import com.google.android.material.tabs.TabLayoutMediator

class ChartsFragment : BaseFragment<FragmentChartsBinding, ChartsViewModel>() {

    private lateinit var adapter: ChartsPagerAdapter

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
        adapter = ChartsPagerAdapter(this)
        binding.viewPager.adapter = adapter

        val tabLayout = TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Pie Chart"
                }

                1 -> {
                    tab.text = "Bar Chart"
                }

                2 -> {
                    tab.text = "Radar Chart"
                }

                else -> {
                    tab.text = "Pie Chart"
                }
            }
        }
        tabLayout.attach()

    }

    override fun initializeEvents() {
        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}