package com.android.apps.appWallpaper.screens

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.MediaController
import androidx.lifecycle.ViewModelProvider
import com.android.commons.base.BaseFragment
import com.android.databinding.FragmentWallpaperBinding

class WallpaperFragment : BaseFragment<FragmentWallpaperBinding, WallpaperViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWallpaperBinding {
        return FragmentWallpaperBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): WallpaperViewModel {
        return ViewModelProvider(this)[WallpaperViewModel::class.java]
    }

    override fun initializeViews() {

    }

    override fun initializeEvents() {

    }
}