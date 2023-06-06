package com.android.apps.appFakeCall.features.contactInformation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.base.BaseFragment
import com.android.databinding.FragmentContactInformationBinding

class ContactInformationFragment : BaseFragment<
        FragmentContactInformationBinding,
        ContactInformationViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContactInformationBinding {
        return FragmentContactInformationBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): ContactInformationViewModel {
        return ViewModelProvider(this)[ContactInformationViewModel::class.java]
    }

    override fun initializeViews() {
        TODO("Not yet implemented")
    }

    override fun initializeEvents() {
        TODO("Not yet implemented")
    }
}