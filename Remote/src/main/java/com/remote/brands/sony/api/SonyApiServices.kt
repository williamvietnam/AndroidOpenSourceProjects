package com.remote.brands.sony.api

import com.remote.brands.sony.models.PowerStatusResponse
import com.remote.brands.sony.models.ServicesInfoResponse
import io.reactivex.rxjava3.core.Single
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.http.Body
import retrofit2.http.POST

interface SonyApiServices {

    //---------------------------- guide ------------------------------------
    /**
     * @param request: {
     * "method: "getSupportedApiInfo",
     * "id": 5,
     * "params":[
     * {"services":[
     * "system",
     * "avContent"
     * ]}],
     * "version": "1.0"
     * }
     */
    @POST(SonyApiEndpoints.guide)
    fun getSupportedApiInfo(@Body request: JSONObject): Single<ServicesInfoResponse>

    //---------------------------- appControl ------------------------------------
    @POST(SonyApiEndpoints.appControl)
    fun getApplicationList(@Body request: JSONObject)

    @POST(SonyApiEndpoints.appControl)
    fun getApplicationStatusList(@Body request: JSONObject)

    @POST(SonyApiEndpoints.appControl)
    fun getTextForm(@Body request: JSONObject)

    @POST(SonyApiEndpoints.appControl)
    fun getWebAppStatus(@Body request: JSONObject)

    //---------------------------- audio ------------------------------------
    @POST(SonyApiEndpoints.audio)
    fun setAudioMute(@Body request: JSONObject)

    @POST(SonyApiEndpoints.audio)
    fun setAudioVolume(@Body request: JSONObject)

    //----------------------------- system --------------------------------

    @POST(SonyApiEndpoints.system)
    fun setLanguage(@Body request: JSONObject)

    @POST(SonyApiEndpoints.system)
    fun getPowerStatus(@Body request: JSONObject): Single<PowerStatusResponse>

    @POST(SonyApiEndpoints.system)
    fun setPowerStatus(@Body request: JSONObject)

    //-------------------- InfraRed Compatible Control over Internet Protocol ---------------------
    @POST(SonyApiEndpoints.SONY_IRCCCode)
    fun setRemoteController(@Body request: RequestBody)
}