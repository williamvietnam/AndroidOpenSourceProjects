package com.android.vncalling.ui.features.login.selection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.databinding.FragmentMakeSelectionGetPasswordBinding

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
        TODO("Not yet implemented")
    }
}