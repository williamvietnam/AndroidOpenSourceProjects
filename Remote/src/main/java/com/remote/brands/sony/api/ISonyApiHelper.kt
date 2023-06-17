package com.remote.brands.sony.api

import com.remote.brands.sony.models.PowerStatusResponse
import com.remote.brands.sony.models.ServicesInfoResponse
import io.reactivex.rxjava3.core.Single

interface ISonyApiHelper {
    //----------------------------- guide --------------------------------

    fun getSupportedApiInfo(): Single<ServicesInfoResponse>?

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

    //----------------------------- audio --------------------------------
    fun setAudioMute(isMute: Boolean)

    fun setAudioVolume(volume: String)

    //----------------------------- system --------------------------------
    fun setLanguage(language: String)

    fun getPowerStatus(): Single<PowerStatusResponse>?
    fun setPowerStatus(status: Boolean) // true: on state | false: off state

    //-------------------- InfraRed Compatible Control over Internet Protocol ---------------------
    fun setRemoteController(IRCCCode: String)
}