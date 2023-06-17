package com.android.apps.appWAStickers.screens.stickerDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.commons.base.BaseFragment
import com.android.databinding.FragmentWAStickerDetailBinding

class WAStickerDetailFragment :
    BaseFragment<FragmentWAStickerDetailBinding, WAStickerDetailViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWAStickerDetailBinding {
        return FragmentWAStickerDetailBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): WAStickerDetailViewModel {
        return ViewModelProvider(this)[WAStickerDetailViewModel::class.java]
    }

    override fun initializeViews() {
        TODO("Not yet implemented")
    }

    override fun initializeEvents() {
        TODO("Not yet implemented")
    }
}