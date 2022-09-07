package com.android.vncalling.ui.features.tab_personal.security_privacy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.databinding.FragmentSecurityAndPrivacyBinding

class SecurityPrivacyFragment : BaseFragment<
        FragmentSecurityAndPrivacyBinding, SecurityPrivacyViewModel>() {

    override fun createViewModel(): SecurityPrivacyViewModel {
        return ViewModelProvider(this)[SecurityPrivacyViewModel::class.java]
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSecurityAndPrivacyBinding {
        return FragmentSecurityAndPrivacyBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        TODO("Not yet implemented")
    }
}