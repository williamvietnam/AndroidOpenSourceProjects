package com.remote.brands.sony.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ServicesInfoResponse(
    @Expose
    @SerializedName("result")
    var result: MutableList<ServiceInfo>
)

data class ServiceInfo(
    @Expose
    @SerializedName("service")
    var service: String,

    @Expose
    @SerializedName("protocols")
    var protocols: MutableList<String>? = null,

    @Expose
    @SerializedName("apis")
    var apis: MutableList<ApiInfo>,

    @Expose
    @SerializedName("notifications")
    var notifications: MutableList<Notification>? = null
)

data class ApiInfo(
    @Expose
    @SerializedName("name")
    var name: String,

    @Expose
    @SerializedName("versions")
    var versions: MutableList<Version>,
)

data class Notification(
    @Expose
    @SerializedName("name")
    var name: String,

    @Expose
    @SerializedName("versions")
    var versions: MutableList<Version>,
)

data class Version(
    @Expose
    @SerializedName("version")
    var version: String,

    @Expose
    @SerializedName("protocols")
    var protocols: MutableList<String>? = null,

    @Expose
    @SerializedName("authLevel")
    var authLevel: String,
)

