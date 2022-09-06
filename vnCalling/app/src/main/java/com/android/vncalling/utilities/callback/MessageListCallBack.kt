package com.android.vncalling.utilities.callback

import com.android.vncalling.data.remote.models.Message

interface MessageListCallBack {
    fun onItemClicked(item: Message?)
}