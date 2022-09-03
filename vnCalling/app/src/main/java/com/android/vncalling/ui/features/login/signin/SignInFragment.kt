package com.android.vncalling.ui.features.login.signin

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.vncalling.R
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.databinding.FragmentSignInBinding
import com.android.vncalling.ui.features.container.MainActivity

class SignInFragment : BaseFragment<FragmentSignInBinding, SignInViewModel>() {

    companion object {
        val TAG: String = SignInFragment::class.java.simpleName
    }

    override fun createViewModel(): SignInViewModel =
        ViewModelProvider(this)[SignInViewModel::class.java]

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignInBinding = FragmentSignInBinding.inflate(inflater, container, false)

    override fun initialize() {
        this.binding.btnSignIn.setOnClickListener {
            Toast.makeText(context, "Developing", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "debug: sign in clicked")
        }

        this.binding.forgotPassword.setOnClickListener {
            findNavController().navigate(R.id.actionSignInToForgotPassword)
            Log.d(TAG, "debug: navigate from sign in to forgot password screen")
        }

        this.binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.actionToSignUp)
            Log.d(TAG, "debug: navigate from sign in to sign up screen")
        }
    }

    private fun login() {
        val accountName: String = this.binding.accountName.getText()
        val password: String = this.binding.password.getText()
        if (viewModel.isLoginSuccess(accountName, password)) {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        } else {
            Log.d(TAG, "debug: cannot login")
        }
    }
}