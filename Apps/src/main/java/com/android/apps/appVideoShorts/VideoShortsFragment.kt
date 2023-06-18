package com.android.apps.appVideoShorts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.commons.base.BaseFragment
import com.android.databinding.FragmentVideoShortsBinding

class VideoShortsFragment : BaseFragment<FragmentVideoShortsBinding, VideoShortsViewModel>() {
    private lateinit var adapter: VideoShortsAdapter

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentVideoShortsBinding {
        return FragmentVideoShortsBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): VideoShortsViewModel {
        return ViewModelProvider(this)[VideoShortsViewModel::class.java]
    }

    override fun initializeViews() {
        adapter = VideoShortsAdapter(viewModel.initializeData())
        binding.viewPager.adapter = adapter
    }

    override fun initializeEvents() {

    }
}