package com.prank.sounds.fake.videocall.screens.liedetector.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.prank.sounds.fake.videocall.screens.liedetector.models.Question

data class QuestionPack(
    @SerializedName("questionPack")
    @Expose
    var questionPack: String? = null,

    @SerializedName("questions")
    @Expose
    var questions: MutableList<Question>? = null,
) {
    companion object {
        const val ASK_YOUR_QUESTION = "Ask Your Question"
        const val COUPLE = "Couple"
        const val FRIENDS = "Friends"
        const val KIDS = "Kids"
        const val WORK = "Work"
        const val ADULTS = "Adults"
        const val PROBE = "Probe"
    }
}