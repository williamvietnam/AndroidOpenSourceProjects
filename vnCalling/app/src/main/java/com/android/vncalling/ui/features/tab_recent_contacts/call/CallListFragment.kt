package com.android.vncalling.ui.features.tab_recent_contacts.call

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.databinding.FragmentCallListBinding

class CallListFragment : BaseFragment<FragmentCallListBinding, CallListViewModel>() {
    override fun createViewModel(): CallListViewModel =
        ViewModelProvider(this)[CallListViewModel::class.java]

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCallListBinding {
        return FragmentCallListBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        TODO("Not yet implemented")
    }
}