package com.android.apps.appImageFilters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.commons.base.BaseFragment
import com.android.databinding.FragmentImageFiltersBinding

class ImageFiltersFragment : BaseFragment<FragmentImageFiltersBinding, ImageFiltersViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentImageFiltersBinding {
        return FragmentImageFiltersBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): ImageFiltersViewModel {
        return ViewModelProvider(this)[ImageFiltersViewModel::class.java]
    }

    override fun initializeViews() {

    }

    override fun initializeEvents() {
    }
}