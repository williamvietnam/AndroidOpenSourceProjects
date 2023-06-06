package com.android.apps.appPrankSound.soundCategories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.base.BaseFragment
import com.android.databinding.FragmentSoundCateogiesBinding

class SoundCategoriesFragment :
    BaseFragment<FragmentSoundCateogiesBinding, SoundCategoriesViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSoundCateogiesBinding {
        return FragmentSoundCateogiesBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): SoundCategoriesViewModel {
        return ViewModelProvider(this)[SoundCategoriesViewModel::class.java]
    }

    override fun initializeViews() {
        TODO("Not yet implemented")
    }

    override fun initializeEvents() {
        TODO("Not yet implemented")
    }
}