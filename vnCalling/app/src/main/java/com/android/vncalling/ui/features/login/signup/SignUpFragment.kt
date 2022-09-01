package com.android.vncalling.ui.features.login.signup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.databinding.FragmentSignUpBinding

class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>() {
    override fun createViewModel(): SignUpViewModel =
        ViewModelProvider(this)[SignUpViewModel::class.java]

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignUpBinding {
        return FragmentSignUpBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        TODO("Not yet implemented")
    }
}