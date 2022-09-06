package com.android.vncalling.ui.features.login.change_password_success

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.databinding.FragmentChangePasswordSuccessBinding

class ChangePasswordSuccessFragment : BaseFragment<
        FragmentChangePasswordSuccessBinding, ChangePasswordSuccessViewModel>() {
    override fun createViewModel(): ChangePasswordSuccessViewModel =
        ViewModelProvider(this)[ChangePasswordSuccessViewModel::class.java]

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChangePasswordSuccessBinding {
        return FragmentChangePasswordSuccessBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
    }
}