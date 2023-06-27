package com.android.apps.appFakeChat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.commons.base.BaseFragment
import com.android.databinding.FragmentFakeChatBinding

class FakeChatFragment : BaseFragment<FragmentFakeChatBinding, FakeChatViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFakeChatBinding {
        return FragmentFakeChatBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): FakeChatViewModel {
        return ViewModelProvider(this)[FakeChatViewModel::class.java]
    }

    override fun initializeViews() {

    }

    override fun initializeEvents() {

    }
}