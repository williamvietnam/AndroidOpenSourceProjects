package com.android.vncalling.ui.features.login.signup

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.databinding.FragmentSignUpBinding
import com.android.vncalling.ui.custom.toolbar.ToolbarCustom
import java.io.FileNotFoundException
import java.io.InputStream


class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>() {

    companion object {
        val TAG: String = SignUpFragment::class.java.simpleName
        const val RESULT_OK = -1
    }

    private var encodeImage: String? = null

    private val pickImage = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            if (result.data != null) {
                val imageUri: Uri? = result.data?.data
                try {
                    val inputStream: InputStream? =
                        imageUri?.let {
                            context?.contentResolver?.openInputStream(it)
                        }
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    binding.avatar.setImageBitmap(bitmap)
                    binding.tvAddImage.visibility = View.GONE
                    encodeImage = viewModel.encodeBitmapImage(bitmap)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }
            }
        }
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

        //---------------- setup live data to listen events from view model ----------------------
        val showToast: Observer<String> = object : Observer<String> {
            override fun onChanged(message: String?) {
                this@SignUpFragment.showToast(message)
            }
        }
        viewModel.getShowToast().observe(this, showToast)

        val isLoading: Observer<Boolean> = object : Observer<Boolean> {
            override fun onChanged(isLoading: Boolean?) {
                this@SignUpFragment.showLoading(isLoading)
            }
        }
        viewModel.getIsLoading().observe(this, isLoading)

        val isRegisterSuccess: Observer<Boolean> = object : Observer<Boolean> {
            override fun onChanged(isRegisterSuccess: Boolean?) {
                if (isRegisterSuccess != null && isRegisterSuccess) {
                    findNavController().popBackStack()
                }
            }
        }
        viewModel.getIsRegisterSuccess().observe(this, isRegisterSuccess)

        //-------------------------- setup events click in here --------------------------------
        this.binding.toolbar.setToolbarLeftCallBack(object : ToolbarCustom.ToolbarLeftCallBack {
            override fun onLeftClicked() {
                findNavController().popBackStack()
                Log.d(TAG, "debug: come back sign in screen")
            }
        })

        this.binding.flAvatar.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            pickImage.launch(intent)
        }

        this.binding.btnRegister.setOnClickListener {
            register()
        }

        this.binding.btnLoginNow.setOnClickListener {
            findNavController().popBackStack()
            Log.d(TAG, "debug: come back sign in screen")
        }
    }

    private fun register() {
        val avatar = this.encodeImage
        val userName: String = this.binding.userName.getText()
        val accountName: String = this.binding.accountName.getText()
        val password: String = this.binding.password.getText()
        val confirmPassword: String = this.binding.confirmPassword.getText()
        if (viewModel.isValidateRegisterInformation(
                avatarEncodedImage = avatar, userName = userName,
                userAccount = accountName, password = password, confirmPassword = confirmPassword
            )
        ) {
            viewModel.register(
                avatar = avatar!!, userName = userName,
                accountName = accountName, password = password
            )
        }
    }

    private fun showLoading(isLoading: Boolean?) {
        if ((isLoading != null) && isLoading) {
            this.binding.btnRegister.visibility = View.GONE
            this.binding.progressBar.visibility = View.VISIBLE
        } else {
            this.binding.btnRegister.visibility = View.VISIBLE
            this.binding.progressBar.visibility = View.GONE
        }
    }
}