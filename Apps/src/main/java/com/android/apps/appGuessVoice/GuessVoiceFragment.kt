package com.android.apps.appGuessVoice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.commons.base.BaseFragment
import com.android.databinding.FragmentGuessVoiceBinding

class GuessVoiceFragment : BaseFragment<FragmentGuessVoiceBinding, GuessVoiceViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentGuessVoiceBinding {
        return FragmentGuessVoiceBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): GuessVoiceViewModel {
        return ViewModelProvider(this)[GuessVoiceViewModel::class.java]
    }

    override fun initializeViews() {

    }

    override fun initializeEvents() {

    }
}