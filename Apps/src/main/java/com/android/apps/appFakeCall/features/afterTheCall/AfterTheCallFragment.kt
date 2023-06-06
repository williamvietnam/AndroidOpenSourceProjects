package com.android.apps.appFakeCall.features.afterTheCall

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.base.BaseFragment
import com.android.databinding.FragmentAfterTheCallBinding

class AfterTheCallFragment : BaseFragment<FragmentAfterTheCallBinding, AfterTheCallViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAfterTheCallBinding {
        return FragmentAfterTheCallBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): AfterTheCallViewModel {
        return ViewModelProvider(this)[AfterTheCallViewModel::class.java]
    }

    override fun initializeViews() {
        TODO("Not yet implemented")
    }

    override fun initializeEvents() {
        TODO("Not yet implemented")
    }
}