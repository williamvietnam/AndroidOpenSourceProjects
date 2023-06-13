package com.prank.sounds.fake.videocall.screens.liedetector.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("question")
    @Expose
    var question: String? = null
)