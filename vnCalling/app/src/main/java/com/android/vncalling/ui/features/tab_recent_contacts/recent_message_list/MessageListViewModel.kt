package com.android.vncalling.ui.features.tab_recent_contacts.message

import com.android.vncalling.base.BaseViewModel
import com.android.vncalling.data.remote.models.Message

class MessageListViewModel : BaseViewModel() {
    private val messageList: MutableList<Message> = mutableListOf()

    fun getMessageList(): MutableList<Message> {
        this.messageList.clear()
        //todo("add data in here!")
        return this.messageList
    }
}