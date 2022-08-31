package com.android.vncalling.ui.features.tab_recent_contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.databinding.FragmentRecentContactsBinding
import com.google.android.material.tabs.TabLayoutMediator

class RecentContactsFragment : BaseFragment<
        FragmentRecentContactsBinding,
        RecentContactViewModel>() {
    override fun createViewModel(): RecentContactViewModel {
        return ViewModelProvider(this)[RecentContactViewModel::class.java]
    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?)
            : FragmentRecentContactsBinding {
        return FragmentRecentContactsBinding.inflate(inflater, container, false)
    }

    /**
     * document: https://developer.android.com/guide/navigation/navigation-swipe-view-2#kotlin
     * */
    override fun initialize() {
        val recentContactsAdapter = RecentContactsAdapter(this)
        this.binding.viewPager.adapter = recentContactsAdapter

        TabLayoutMediator(binding.tabLayout,
            binding.viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                if (position == 0) {
                    tab.text = "Tin nhắn"
                } else if (position == 1) {
                    tab.text = "Cuộc gọi"
                }
            }).attach()
    }
}