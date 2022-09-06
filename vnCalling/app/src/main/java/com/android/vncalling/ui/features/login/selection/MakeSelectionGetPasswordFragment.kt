package com.android.vncalling.ui.features.login.selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.vncalling.R
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.databinding.FragmentMakeSelectionGetPasswordBinding
import com.android.vncalling.utilities.Constants

class MakeSelectionGetPasswordFragment : BaseFragment<
        FragmentMakeSelectionGetPasswordBinding, MakeSelectionGetPasswordViewModel>() {
    override fun createViewModel(): MakeSelectionGetPasswordViewModel {
        return ViewModelProvider(this)[MakeSelectionGetPasswordViewModel::class.java]
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMakeSelectionGetPasswordBinding {
        return FragmentMakeSelectionGetPasswordBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        this.binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        this.binding.textNumberPhone.text =
            String.format("via sms:\n%s", viewModel.getNumberPhone())

        this.binding.textEmail.text =
            String.format("via email:\n%s", viewModel.getEmail())

        this.binding.btnViaSms.setOnClickListener {
            val args = Bundle()
            args.putString(Constants.FORGET_PASSWORD_VIA_SMS_OR_EMAIL, viewModel.getNumberPhone())
            findNavController().navigate(R.id.actionMakeSelectionToVerifyCode, args)
        }

        this.binding.btnViaEmail.setOnClickListener {
            val args = Bundle()
            args.putString(Constants.FORGET_PASSWORD_VIA_SMS_OR_EMAIL, viewModel.getEmail())
            findNavController().navigate(R.id.actionMakeSelectionToVerifyCode, args)
        }
    }
}