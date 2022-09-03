package com.android.vncalling.ui.features.login.forgot_password

import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.vncalling.R
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : BaseFragment<
        FragmentForgotPasswordBinding, ForgotPasswordViewModel>() {

    companion object {
        val TAG: String = ForgotPasswordFragment::class.java.simpleName
    }

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
        this.binding.btnBack.setOnClickListener {
            if (viewModel.isNullOrEmpty(binding.textFieldAccountName.text.toString())) {
                findNavController().popBackStack()
                Log.d(TAG, "debug: come back sign in screen")
            } else {
                AlertDialog.Builder(requireContext())
                    .setTitle("Attention")
                    .setMessage("Entered information will be deleted if you click continue")
                    .setPositiveButton("Continue", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, id: Int) {
                            findNavController().popBackStack()
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .setCancelable(false)
                    .show()
            }
        }

        this.binding.buttonNext.setOnClickListener {
            if (viewModel.isValidateTextField(this.binding.textFieldAccountName.text.toString())) {
                findNavController().navigate(R.id.actionForgotPassToMakeSelection)
                Log.d(TAG, "debug: next to make selection screen")
            }
        }
    }
}