package com.remote.brands.sony.api

import com.remote.brands.sony.models.PowerStatusParam
import com.remote.brands.sony.models.SonyServiceParam
import com.remote.brands.sony.models.SonyVolumeParam
import com.remote.brands.sony.models.requests.SonyRequest
import com.remote.brands.sony.models.responses.PowerStatusResponse
import com.remote.brands.sony.models.responses.ServicesInfoResponse
import io.reactivex.rxjava3.core.Single
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class SonyApiImplement : ISonyApiHelper {


    //------------------------------guide--------------------------------------
    override fun getSupportedApiInfo(): Single<ServicesInfoResponse>? {
        val sonyServices: MutableList<String> = ArrayList()
        sonyServices.add("appControl")
        sonyServices.add("audio")
        sonyServices.add("avContent")
        sonyServices.add("encryption")
        sonyServices.add("system")
        sonyServices.add("video")
        sonyServices.add("videoScreen")
        val sonyServiceParam = SonyServiceParam(sonyServices)
        val sonyServiceParams: MutableList<SonyServiceParam> = ArrayList()
        sonyServiceParams.add(sonyServiceParam)
        val request: SonyRequest<SonyServiceParam> = SonyRequest(
            method = "getSupportedApiInfo",
            id = 5,
            params = sonyServiceParams
        )
        return SonyApiClient.instance?.getApiService()?.getSupportedApiInfo(request)
    }

    override fun getSupportedAppControlServicesInfo(): Single<ServicesInfoResponse>? {
        val sonyServices: MutableList<String> = ArrayList()
        sonyServices.add("appControl")
        val sonyServiceParam = SonyServiceParam(sonyServices)
        val sonyServiceParams: MutableList<SonyServiceParam> = ArrayList()
        sonyServiceParams.add(sonyServiceParam)
        val request: SonyRequest<SonyServiceParam> = SonyRequest(
            method = "getSupportedApiInfo",
            id = 5,
            params = sonyServiceParams
        )
        return SonyApiClient.instance?.getApiService()?.getSupportedApiInfo(request)
    }

    override fun getSupportedAudioServicesInfo() {

    }

    override fun getSupportedAvContentServicesInfo() {

    }

    override fun getSupportedEncryptionServicesInfo() {

    }

    override fun getSupportedSystemServicesInfo() {

    }

    override fun getSupportedVideoServicesInfo() {

    }

    override fun getSupportedVideoScreenServicesInfo() {

    }

    //----------------------------appControl--------------------------------------
    override fun getApplicationList() {

    }

    override fun getApplicationStatusList() {

    }

    override fun getTextForm() {

    }

    override fun getWebAooStatus() {

    }

    override fun setActiveApp() {

    }

    override fun setTextForm() {

    }

    override fun terminateApps() {

    }

    //----------------------------audio--------------------------------------
    override fun setAudioMute(isMute: Boolean) {
        val request = JSONObject()
        try {
            request.put("method", "setAudioMute")

            request.put("id", 601)

            val paramsArray = JSONArray()
            paramsArray.put(JSONObject().put("status", isMute))
            request.put("params", paramsArray)

            request.put("version", "1.0")

            SonyApiClient.instance?.getApiService()?.setAudioMute(request)
        } catch (jsonException: JSONException) {
            jsonException.printStackTrace()
        }
    }

    override fun setAudioVolume(volume: String) {
        val params: MutableList<SonyVolumeParam> = ArrayList()
        params.add(SonyVolumeParam(volume))
        val request = SonyRequest<SonyVolumeParam>("setAudioVolume", 601, params)
        SonyApiClient.instance?.getApiService()?.setAudioVolume(request)
    }

    //----------------------------- system --------------------------------
    override fun setLanguage(language: String) {
        val request = JSONObject()
        try {
            request.put("method", "setLanguage")
            request.put("id", 55)
            request.put("params", JSONArray().put(JSONObject().put("language", language)))
            request.put("version", "1.0")
            SonyApiClient.instance?.getApiService()?.setLanguage(request)
        } catch (jsonException: JSONException) {
            jsonException.printStackTrace()
        }
    }

    override fun getPowerStatus(): Single<PowerStatusResponse>? {
        val request = JSONObject()
        var response: Single<PowerStatusResponse>? = null
        try {
            request.put("method", "getPowerStatus")
            request.put("id", 50)
            request.put("params", JSONArray())
            request.put("version", "1.0")
            response = SonyApiClient.instance?.getApiService()?.getPowerStatus(request)
        } catch (jsonException: JSONException) {
            jsonException.printStackTrace()
        }
        return response
    }

    override fun setPowerStatus(status: Boolean) {
        val params: MutableList<PowerStatusParam> = ArrayList()
        params.add(PowerStatusParam(status))
        val request = SonyRequest("setPowerStatus", 55, params)
        SonyApiClient.instance?.getApiService()?.setPowerStatus(request)
    }

    //-------------------- InfraRed Compatible Control over Internet Protocol ---------------------
    override fun setRemoteController(IRCCCode: String) {
        try {

        } catch (jsonException: JSONException) {
            jsonException.printStackTrace()
        }
    }
}