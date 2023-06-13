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
        const val APP_PRANK_SOUND_ID = "app.prank.sound.id"
        const val APP_FAKE_CALL_ID = "app.fake.call.id"
        const val APP_LIE_DETECTOR_ID = "app.lie.detector.id"
        const val APP_AI_CHAT_ID = "app.ai.chat.id"
        const val APP_WHATSAPP_STICKERS_ID = "app.whatsapp.stickers.id"
        const val APP_PAINT_ID = "app.paint.id"
        const val APP_IMAGE_FILTERS_ID = "app.image.filters.id"

        // Apps Name
        const val APP_PRANK_SOUND_NAME = "Prank Sounds"
        const val APP_FAKE_CALL_NAME = "Fake Call"
        const val APP_LIE_DETECTOR_NAME = "Lie Detector"
        const val APP_AI_CHAT_NAME = "AI Chat"
        const val APP_WHATSAPP_STICKERS_NAME = "WA Stickers"
        const val APP_PAINT_NAME = "Paint"
        const val APP_IMAGE_FILTERS_NAME = "Image Filters"
    }
}