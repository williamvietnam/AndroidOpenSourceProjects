package com.remote.brands.sony.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PowerStatusResponse(
    @Expose
    @SerializedName("result")
    var result: MutableList<PowerStatus>,

    @Expose
    @SerializedName("id")
    var id: Int
)

data class PowerStatus(
    @Expose
    @SerializedName("status")
    var status: String // standby: off | active: on
)