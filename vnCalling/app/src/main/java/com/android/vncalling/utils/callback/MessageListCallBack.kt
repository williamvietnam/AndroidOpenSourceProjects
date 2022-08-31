package com.android.vncalling.utils.callback

import com.android.vncalling.data.remote.models.Message

interface MessageListCallBack {
    fun onItemClicked(item: Message?)
}