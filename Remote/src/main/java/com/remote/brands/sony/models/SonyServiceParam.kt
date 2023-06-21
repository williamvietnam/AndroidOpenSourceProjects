package com.remote.brands.sony.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SonyServiceParam(
    @SerializedName("service")
    @Expose
    var services: MutableList<String>
)

