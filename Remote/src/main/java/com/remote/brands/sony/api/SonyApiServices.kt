package com.remote.brands.sony.api

import org.json.JSONObject
import retrofit2.http.POST

interface SonyApiServices {

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
    fun getSupportedApiInfo(request: JSONObject)

    @POST(SonyApiEndpoints.appControl)
    fun getApplicationStatusList(request: JSONObject)


}