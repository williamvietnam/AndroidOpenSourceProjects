package com.remote.brands.sony.api

interface ISonyApiHelper {
    //----------------------------- guide --------------------------------

    fun getSupportedApiInfo()

    fun getSupportedAppControlServicesInfo()

    fun getSupportedAudioServicesInfo()

    fun getSupportedAvContentServicesInfo()

    fun getSupportedEncryptionServicesInfo()

    fun getSupportedSystemServicesInfo()

    fun getSupportedVideoServicesInfo()

    fun getSupportedVideoScreenServicesInfo()

    //----------------------------- avControl --------------------------------

    fun getApplicationList()

    fun getApplicationStatusList()

    fun getTextForm()

    fun getWebAooStatus()

    fun setActiveApp()

    fun setTextForm()

    fun terminateApps()
}