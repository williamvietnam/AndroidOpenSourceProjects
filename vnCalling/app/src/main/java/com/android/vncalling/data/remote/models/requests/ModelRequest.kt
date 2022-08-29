package com.android.vncalling.data.remote.models.requests

import com.android.vncalling.data.remote.models.Model

data class ModelRequest(
    val requestId: String,
    val model: Model
)