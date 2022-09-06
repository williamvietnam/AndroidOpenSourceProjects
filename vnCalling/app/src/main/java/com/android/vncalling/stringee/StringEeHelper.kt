package com.android.vncalling.stringee

import com.stringee.StringeeClient
import com.stringee.listener.StringeeConnectionListener

/**
 * docs source: https://developer.stringee.com/docs/getting-started/call-api-getting-started-android2
 * **/
interface StringEeHelper : StringeeConnectionListener {
    fun getClient(): StringeeClient

    fun connectToServer(token: String?, connectionListener: StringeeConnectionListener?)

    fun connectToServer(token: String?)
}