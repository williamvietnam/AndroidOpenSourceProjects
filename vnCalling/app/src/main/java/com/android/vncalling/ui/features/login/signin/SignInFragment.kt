package com.android.vncalling.ui.features.login.signin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.databinding.FragmentSignInBinding

class SignInFragment : BaseFragment<FragmentSignInBinding, SignInViewModel>() {
    override fun createViewModel(): SignInViewModel =
        ViewModelProvider(this)[SignInViewModel::class.java]

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignInBinding = FragmentSignInBinding.inflate(inflater, container, false)

    override fun initialize() {
        TODO("Not yet implemented")
    }
}