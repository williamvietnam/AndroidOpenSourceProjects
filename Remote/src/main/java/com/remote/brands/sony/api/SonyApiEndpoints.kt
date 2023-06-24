package com.remote.brands.sony.api

object SonyApiEndpoints {
    var SONY_BASE_URL = ""
    var SONY_PRE_SHARED_KEY = ""

    fun initializeSonyBaseUrl(apiAddress: String) {
        SONY_BASE_URL = "$apiAddress/sony/"
    }

    fun initializeSonyPreSharedKey(preSharedKey: String) {
        this.SONY_PRE_SHARED_KEY = preSharedKey
    }

    const val guide = "/guide"
    const val appControl = "/appControl"
    const val audio = "/audio"
    const val avContent = "/avContent"
    const val encryption = "/encryption"
    const val system = "/system"
    const val video = "/video"
    const val videoScreen = "/videoScreen"
    const val SONY_IRCCCode = "/ircc"
}