package com.android.vncalling.ui.features.tab_recent_contacts.message

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.databinding.FragmentMessageListBinding

class MessageListFragment : BaseFragment<FragmentMessageListBinding, MessageListViewModel>() {

    override fun createViewModel(): MessageListViewModel =
        ViewModelProvider(this)[MessageListViewModel::class.java]

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMessageListBinding {
        return FragmentMessageListBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        TODO("Not yet implemented")
    }
}