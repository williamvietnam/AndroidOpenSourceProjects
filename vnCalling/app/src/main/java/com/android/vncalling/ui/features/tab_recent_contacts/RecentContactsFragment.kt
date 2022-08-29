package com.android.vncalling.ui.features.tab_recent_contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.databinding.FragmentRecentContactsBinding

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

    override fun initialize() {
        binding.text.text = viewModel.showViewModelName(RecentContactViewModel::class.java.simpleName)
    }
}