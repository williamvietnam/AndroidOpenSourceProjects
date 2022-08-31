package com.android.vncalling.ui.features.tab_personal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.databinding.FragmentPersonalBinding

class PersonalFragment : BaseFragment<FragmentPersonalBinding, PersonalViewModel>() {

    override fun createViewModel(): PersonalViewModel =
        ViewModelProvider(this)[PersonalViewModel::class.java]

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPersonalBinding {
        return FragmentPersonalBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        TODO("Not yet implemented")
    }
}