package com.android.apps.appFakeCall.features.contactInformation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.apps.appFakeCall.data.entities.ContactEntity
import com.android.base.BaseFragment
import com.android.container.Constants
import com.android.container.Preferences
import com.android.databinding.FragmentContactInformationBinding

class ContactInformationFragment : BaseFragment<
        FragmentContactInformationBinding,
        ContactInformationViewModel>() {
    private var contact: ContactEntity? = null
    private var isRecord: Boolean = false

    private var timeCall: Int = 0

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
        Preferences.instance.set(Constants.IS_FAKE_CALL_RECORD, false)
    }

    override fun initializeEvents() {
        TODO("Not yet implemented")
    }
}