package com.android.vncalling.ui.features.login.update_new_password

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.databinding.FragmentUpdateNewPasswordBinding

class UpdateNewPasswordFragment : BaseFragment<
        FragmentUpdateNewPasswordBinding, UpdateNewPasswordViewModel>() {
    override fun createViewModel(): UpdateNewPasswordViewModel {
        return ViewModelProvider(this)[UpdateNewPasswordViewModel::class.java]
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentUpdateNewPasswordBinding {
        return FragmentUpdateNewPasswordBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
    }
}