package com.android.vncalling.ui.features.tab_contacts_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.databinding.FragmentContactListBinding

class ContactListFragment : BaseFragment<FragmentContactListBinding, ContactListViewModel>() {

    override fun createViewModel(): ContactListViewModel =
        ViewModelProvider(this)[ContactListViewModel::class.java]

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContactListBinding {
        return FragmentContactListBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        TODO("Not yet implemented")
    }
}