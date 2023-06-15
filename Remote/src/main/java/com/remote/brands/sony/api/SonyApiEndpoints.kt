package com.remote.brands.sony.api

object SonyApiEndpoints {
    var SONY_BASE_URL = ""

    fun initializeSonyBaseUrl(apiAddress: String) {
        SONY_BASE_URL = apiAddress
    }

    const val guide = "/guide"
    const val appControl = "/appControl"
    const val audio = "/audio"
    const val avContent = "/avContent"
    const val encryption = "/encryption"
    const val system = "/system"
    const val video = "/video"
    const val videoScreen = "/videoScreen"
}