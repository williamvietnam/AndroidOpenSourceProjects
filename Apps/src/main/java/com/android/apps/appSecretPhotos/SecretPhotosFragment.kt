package com.android.apps.appSecretPhotos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.commons.base.BaseFragment
import com.android.databinding.FragmentSecretPhotosBinding

class SecretPhotosFragment : BaseFragment<FragmentSecretPhotosBinding, SecretPhotosViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSecretPhotosBinding {
        return FragmentSecretPhotosBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): SecretPhotosViewModel {
        return ViewModelProvider(this)[SecretPhotosViewModel::class.java]
    }

    override fun initializeViews() {

    }

    override fun initializeEvents() {

    }
}