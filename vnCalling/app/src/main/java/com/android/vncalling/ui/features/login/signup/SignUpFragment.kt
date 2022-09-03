package com.android.vncalling.ui.features.login.signup

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.databinding.FragmentSignUpBinding
import com.android.vncalling.ui.custom.toolbar.ToolbarCustom

class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>() {

    companion object {
        val TAG: String = SignUpFragment::class.java.simpleName
    }

    override fun createViewModel(): SignUpViewModel =
        ViewModelProvider(this)[SignUpViewModel::class.java]

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignUpBinding {
        return FragmentSignUpBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        this.binding.toolbar.setToolbarLeftCallBack(object : ToolbarCustom.ToolbarLeftCallBack {
            override fun onLeftClicked() {
                findNavController().popBackStack()
                Log.d(TAG, "debug: come back sign in screen")
            }
        })

        this.binding.btnRegister.setOnClickListener {
            Toast.makeText(context, "developing", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "debug: register clicked")
        }

        this.binding.btnLoginNow.setOnClickListener {
            findNavController().popBackStack()
            Log.d(TAG, "debug: come back sign in screen")
        }
    }

    private fun register() {
        val avatar = ""
        val userName: String = this.binding.userName.getText()
        val accountName: String = this.binding.accountName.getText()
        val password: String = this.binding.password.getText()
        val confirmPassword: String = this.binding.confirmPassword.getText()
        if (viewModel.isRegisterSuccess(avatar, userName, accountName, password, confirmPassword)) {
            findNavController().popBackStack()
            Log.d(TAG, "register account success")
        }
    }
}