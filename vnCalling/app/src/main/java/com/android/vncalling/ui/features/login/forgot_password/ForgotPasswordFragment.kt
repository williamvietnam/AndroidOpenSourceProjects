package com.android.vncalling.ui.features.login.forgot_password

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : BaseFragment<
        FragmentForgotPasswordBinding, ForgotPasswordViewModel>() {
    override fun createViewModel(): ForgotPasswordViewModel {
        return ViewModelProvider(this)[ForgotPasswordViewModel::class.java]
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentForgotPasswordBinding {
        return FragmentForgotPasswordBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
    }
}