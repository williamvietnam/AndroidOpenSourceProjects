package com.remote.brands.sony.api

import com.remote.brands.sony.models.PowerStatusResponse
import com.remote.brands.sony.models.ServicesInfoResponse
import io.reactivex.rxjava3.core.Single
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class SonyApiImplement : ISonyApiHelper {


    //------------------------------guide--------------------------------------
    override fun getSupportedApiInfo(): Single<ServicesInfoResponse>? {
        val request = JSONObject()
        var response: Single<ServicesInfoResponse>? = null
        try {
            request.put("method", "getSupportedApiInfo")
            request.put("id", 5)
            val services = JSONArray()
            request.put("params", services)
            request.put("version", "1.0")
            response = SonyApiClient.instance?.getApiService()?.getSupportedApiInfo(request)
        } catch (jsonException: JSONException) {
            jsonException.printStackTrace()
        }
        return response
    }

    override fun getSupportedAppControlServicesInfo() {

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
        val request = JSONObject()
        try {
            request.put("method", "setAudioVolume")

            request.put("id", 601)

            val paramsArray = JSONArray()
            paramsArray.put(JSONObject().put("volume", volume))
            paramsArray.put(JSONObject().put("target", "speaker"))
            request.put("params", paramsArray)

            request.put("version", "1.0")

            SonyApiClient.instance?.getApiService()?.setAudioVolume(request)
        } catch (jsonException: JSONException) {
            jsonException.printStackTrace()
        }
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
        val request = JSONObject()
        try {
            request.put("method", "setPowerStatus")
            request.put("id", 55)
            request.put("params", JSONArray().put(JSONObject().put("status", status)))
            request.put("version", "1.0")
            SonyApiClient.instance?.getApiService()?.setPowerStatus(request)
        } catch (jsonException: JSONException) {
            jsonException.printStackTrace()
        }
    }

    //-------------------- InfraRed Compatible Control over Internet Protocol ---------------------
    override fun setRemoteController(IRCCCode: String) {
        try {

        } catch (jsonException: JSONException) {
            jsonException.printStackTrace()
        }
    }
}