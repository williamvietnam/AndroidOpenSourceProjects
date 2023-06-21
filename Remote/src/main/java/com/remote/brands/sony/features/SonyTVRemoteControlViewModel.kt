package com.remote.brands.sony.features

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.remote.brands.sony.models.responses.ServicesInfoResponse
import com.remote.commons.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SonyTVRemoteControlViewModel : BaseViewModel() {
    fun getSupportedServicesInfo(): LiveData<ServicesInfoResponse> {
        val result: MutableLiveData<ServicesInfoResponse> = MutableLiveData()
        this.getCompositeDisposable().add(
            this.getSonyApis().getSupportedApiInfo()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    result.postValue(it)
                }, {
                    Log.d("SonyTVRemoteControlViewModel", "getSupportedServicesInfo()... failure")
                })!!
        )
        return result
    }

    fun setAudioMute(isMute: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            getSonyApis().setAudioMute(isMute)
        }
    }

    fun setAudioVolume(volume: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getSonyApis().setAudioVolume(volume)
        }
    }

    fun setPowerStatus(status: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            getSonyApis().setPowerStatus(status)
        }
    }

    fun setRemoteController(key: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getSonyApis().setRemoteController(key)
        }
    }

    
}