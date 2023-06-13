package com.prank.sounds.fake.videocall.screens.liedetector.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class QuestionPacksResponse(
    @SerializedName("questionPacks")
    @Expose
    var questionPacks: MutableList<QuestionPack>? = null
)