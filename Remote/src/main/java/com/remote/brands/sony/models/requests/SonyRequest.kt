package com.remote.brands.sony.models.requests

data class SonyRequest<T>(
    var method: String,

    var id: Int,

    var params: MutableList<T>,

    var version: String = "1.0"
)