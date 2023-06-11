package com.android.apps.appPaint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.core.base.BaseFragment
import com.android.databinding.FragmentPaintBinding

class PaintFragment : BaseFragment<FragmentPaintBinding, PaintViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPaintBinding {
        return FragmentPaintBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): PaintViewModel {
        return ViewModelProvider(this)[PaintViewModel::class.java]
    }

    override fun initializeViews() {

    }

    override fun initializeEvents() {

    }
}