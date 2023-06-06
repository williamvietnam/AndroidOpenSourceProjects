package com.android.apps.appFakeCall.features.newContact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.base.BaseFragment
import com.android.databinding.FragmentNewContactBinding

class NewContactFragment : BaseFragment<FragmentNewContactBinding, NewContactViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNewContactBinding {
        return FragmentNewContactBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): NewContactViewModel {
        return ViewModelProvider(this)[NewContactViewModel::class.java]
    }

    override fun initializeViews() {
        TODO("Not yet implemented")
    }

    override fun initializeEvents() {
        TODO("Not yet implemented")
    }
}