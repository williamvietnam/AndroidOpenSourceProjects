package com.android.container.appsMenu

/**
 * Author: William Vietnam |
 * 28/03/2023
 * */
data class App(
    val id: String,
    val logo: Int,
    val name: String,
) {
    companion object {
        // Apps ID
        const val APP_PRANK_SOUND_ID = "appPrankSoundId"
        const val APP_FAKE_CALL_ID = "appFakeCallId"
        const val APP_LIE_DETECTOR_ID = "appLieDetectorId"
        const val APP_AI_CHAT_ID = "appAIChatId"

        // Apps Name
        const val APP_PRANK_SOUND_NAME = "Prank Sounds"
        const val APP_FAKE_CALL_NAME = "Fake Call"
        const val APP_LIE_DETECTOR_NAME = "Lie Detector"
        const val APP_AI_CHAT_NAME = "AI Chat"
    }
}