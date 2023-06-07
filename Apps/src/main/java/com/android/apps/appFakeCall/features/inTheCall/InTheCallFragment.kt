package com.android.apps.appFakeCall.features.inTheCall

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.base.BaseFragment
import com.android.databinding.FragmentInTheCallBinding

class InTheCallFragment : BaseFragment<FragmentInTheCallBinding, InTheCallViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInTheCallBinding {
        return FragmentInTheCallBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): InTheCallViewModel {
        return ViewModelProvider(this)[InTheCallViewModel::class.java]
    }

    override fun initializeViews() {
        TODO("Not yet implemented")
    }

    override fun initializeEvents() {
        TODO("Not yet implemented")
    }
}