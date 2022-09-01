package com.android.vncalling.ui.features.login.verify

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.databinding.FragmentVerifyCodeBinding

class VerifyCodeFragment : BaseFragment<FragmentVerifyCodeBinding, VerifyCodeViewModel>() {
    override fun createViewModel(): VerifyCodeViewModel =
        ViewModelProvider(this)[VerifyCodeViewModel::class.java]

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentVerifyCodeBinding {
        return FragmentVerifyCodeBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        TODO("Not yet implemented")
    }
}