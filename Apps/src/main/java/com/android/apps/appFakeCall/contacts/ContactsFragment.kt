package com.android.apps.appFakeCall.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.base.BaseFragment
import com.android.databinding.FragmentContactsBinding

class ContactsFragment : BaseFragment<FragmentContactsBinding, ContactsViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContactsBinding {
        return FragmentContactsBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): ContactsViewModel {
        return ViewModelProvider(this)[ContactsViewModel::class.java]
    }

    override fun initializeView() {
        TODO("Not yet implemented")
    }
}