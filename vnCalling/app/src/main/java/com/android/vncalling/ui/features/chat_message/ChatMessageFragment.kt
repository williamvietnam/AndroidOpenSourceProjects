package com.android.vncalling.ui.features.chat_message

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.vncalling.base.BaseFragment
import com.android.vncalling.data.remote.models.Message
import com.android.vncalling.databinding.FragmentChatMessageBinding

class ChatMessageFragment : BaseFragment<FragmentChatMessageBinding, ChatMessageViewModel>() {

    override fun createViewModel(): ChatMessageViewModel {
        return ViewModelProvider(this)[ChatMessageViewModel::class.java]
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChatMessageBinding {
        return FragmentChatMessageBinding.inflate(inflater, container, false)
    }

    override fun initialize() {

        //------------------------------ setOnClick ---------------------------------------------
        this.binding.toolbar.btnBack.setOnClickListener {
            //todo("")
        }
        this.binding.toolbar.btnVideoCall.setOnClickListener {
            //todo("")
        }
        this.binding.toolbar.btnAudioCall.setOnClickListener {
            //todo("")
        }
        this.binding.toolbar.btnMore.setOnClickListener {
            //todo("")
        }
    }

    private fun setupChatMessageToolbar(message: Message) {

    }
}