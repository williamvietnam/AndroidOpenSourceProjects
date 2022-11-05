package com.android.vncalling.ui.features.login.verify

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.vncalling.R
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
        this.binding.btnBack.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("")
                .setMessage("")
                .setPositiveButton("Continue", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, id: Int) {
                        findNavController().popBackStack()
                    }
                })
                .setNegativeButton("Cancel", null)
                .setCancelable(false)
                .show()
        }

        this.binding.resendOtp.setOnClickListener{
            //todo("")
        }

        this.binding.btnVerify.setOnClickListener {
            if (viewModel.isVerifyCodeSuccess(
                    firstInput = this.binding.inputFirst.text.toString().trim(),
                    secondInput = this.binding.inputSecond.text.toString().trim(),
                    thirdInput = this.binding.inputThird.text.toString().trim(),
                    fourthInput = this.binding.inputFourth.text.toString().trim()
                )
            ) {
                findNavController().navigate(R.id.actionVerifyCodeToUpdateNewPassword)
            }
        }
    }
}