package com.android.apps.appFakeBanking.techcombank

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.commons.base.BaseFragment
import com.android.databinding.FragmentTechcombankBinding

class TechcombankFragment : BaseFragment<FragmentTechcombankBinding, TechcombankViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTechcombankBinding {
        return FragmentTechcombankBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): TechcombankViewModel {
        return ViewModelProvider(this)[TechcombankViewModel::class.java]
    }

    override fun initializeViews() {

    }

    override fun initializeEvents() {

    }
}