package com.android.apps.appPrankSound.sound

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.core.base.BaseFragment
import com.android.databinding.FragmentSoundBinding

class SoundFragment : BaseFragment<FragmentSoundBinding, SoundViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSoundBinding {
        return FragmentSoundBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): SoundViewModel {
        return ViewModelProvider(this)[SoundViewModel::class.java]
    }

    override fun initializeViews() {

    }

    override fun initializeEvents() {
    }
}