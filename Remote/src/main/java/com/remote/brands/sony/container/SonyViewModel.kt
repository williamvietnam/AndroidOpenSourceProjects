package com.remote.brands.sony.container

import androidx.lifecycle.viewModelScope
import com.remote.brands.sony.api.SonyApiEndpoints
import com.remote.commons.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SonyViewModel : BaseViewModel() {

    fun setIPAddress(ipAddress: String) {
        SonyApiEndpoints.initializeSonyBaseUrl(ipAddress)
    }

    fun setPreShredKey(preShredKey: String) {
        SonyApiEndpoints.initializeSonyPreSharedKey(preShredKey)
    }

    fun setPowerStatus(status: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            getSonyApis().setPowerStatus(status)
        }
    }

    fun setAudioVolume(volume: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getSonyApis().setAudioVolume(volume)
        }
    }

    fun setAudioMute(status: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            getSonyApis().setAudioMute(isMute = status)
        }
    }

    fun setRemoteController(IRCCCode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getSonyApis().setRemoteController(IRCCCode)
        }
    }
}