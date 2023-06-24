package com.remote.brands.sony.api

import com.remote.brands.sony.models.PowerStatusParam
import com.remote.brands.sony.models.SonyServiceParam
import com.remote.brands.sony.models.SonyVolumeParam
import com.remote.brands.sony.models.requests.SonyRequest
import com.remote.brands.sony.models.responses.PowerStatusResponse
import com.remote.brands.sony.models.responses.ServicesInfoResponse
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
    fun getSupportedApiInfo(@Body request: SonyRequest<SonyServiceParam>): Single<ServicesInfoResponse>

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
    fun setAudioVolume(@Body request: SonyRequest<SonyVolumeParam>)

    //----------------------------- system --------------------------------

    @POST(SonyApiEndpoints.system)
    fun setLanguage(@Body request: JSONObject)

    @POST(SonyApiEndpoints.system)
    fun getPowerStatus(@Body request: JSONObject): Single<PowerStatusResponse>

    @POST(SonyApiEndpoints.system)
    fun setPowerStatus(@Body request: SonyRequest<PowerStatusParam>)

    //-------------------- InfraRed Compatible Control over Internet Protocol ---------------------
    @POST(SonyApiEndpoints.SONY_IRCCCode)
    fun setRemoteController(@Body request: RequestBody)
}