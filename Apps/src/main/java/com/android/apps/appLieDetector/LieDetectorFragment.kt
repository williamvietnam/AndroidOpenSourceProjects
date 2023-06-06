package com.android.apps.appLieDetector

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.base.BaseFragment
import com.android.databinding.FragmentLieDetectorBinding

class LieDetectorFragment : BaseFragment<FragmentLieDetectorBinding, LieDetectorViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLieDetectorBinding {
        return FragmentLieDetectorBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): LieDetectorViewModel {
        return ViewModelProvider(this)[LieDetectorViewModel::class.java]
    }

    override fun initializeViews() {
    }

    override fun initializeEvents() {
    }
}