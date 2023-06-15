package com.remote.brands.sony.api

import org.json.JSONException
import org.json.JSONObject

class SonyApiImplement : ISonyApiHelper {


    //------------------------------guide--------------------------------------
    override fun getSupportedApiInfo() {
        val request: JSONObject = JSONObject()
        try {
        } catch (jsonException: JSONException) {
            jsonException.printStackTrace()
        }
    }

    override fun getSupportedAppControlServicesInfo() {
        TODO("Not yet implemented")
    }

    override fun getSupportedAudioServicesInfo() {
        TODO("Not yet implemented")
    }

    override fun getSupportedAvContentServicesInfo() {
        TODO("Not yet implemented")
    }

    override fun getSupportedEncryptionServicesInfo() {
        TODO("Not yet implemented")
    }

    override fun getSupportedSystemServicesInfo() {
        TODO("Not yet implemented")
    }

    override fun getSupportedVideoServicesInfo() {
        TODO("Not yet implemented")
    }

    override fun getSupportedVideoScreenServicesInfo() {
        TODO("Not yet implemented")
    }

    //----------------------------appControl--------------------------------------
    override fun getApplicationList() {
        TODO("Not yet implemented")
    }

    override fun getApplicationStatusList() {
        TODO("Not yet implemented")
    }

    override fun getTextForm() {
        TODO("Not yet implemented")
    }

    override fun getWebAooStatus() {
        TODO("Not yet implemented")
    }

    override fun setActiveApp() {
        TODO("Not yet implemented")
    }

    override fun setTextForm() {
        TODO("Not yet implemented")
    }

    override fun terminateApps() {
        TODO("Not yet implemented")
    }

    //----------------------------audio--------------------------------------
}