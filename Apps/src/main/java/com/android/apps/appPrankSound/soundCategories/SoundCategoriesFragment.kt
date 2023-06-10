package com.android.apps.appPrankSound.soundCategories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.base.BaseFragment
import com.android.databinding.FragmentSoundCategoriesBinding

class SoundCategoriesFragment : BaseFragment<
        FragmentSoundCategoriesBinding,
        SoundCategoriesViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSoundCategoriesBinding.inflate(inflater, container, false)

    override fun createViewModel(): SoundCategoriesViewModel {
        return ViewModelProvider(this)[SoundCategoriesViewModel::class.java]
    }

    override fun initializeView() {
        TODO("Not yet implemented")
    }
}