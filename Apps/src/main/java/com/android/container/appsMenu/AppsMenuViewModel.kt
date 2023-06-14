package com.android.container.appsMenu

import com.android.R
import com.android.core.base.BaseViewModel

class AppsMenuViewModel : BaseViewModel() {
    fun createApps(): MutableList<App> {
        val apps = ArrayList<App>()

        val appPrankSound = App(
            id = App.APP_PRANK_SOUND_ID,
            logo = R.drawable.app_prank_sound_logo,
            name = App.APP_PRANK_SOUND_NAME
        )
        apps.add(appPrankSound)

        val appFakeCall = App(
            id = App.APP_FAKE_CALL_ID,
            logo = R.drawable.app_fake_call_logo,
            name = App.APP_FAKE_CALL_NAME
        )
        apps.add(appFakeCall)

        val appLieDetector = App(
            id = App.APP_LIE_DETECTOR_ID,
            logo = R.drawable.app_lie_detector_logo_v2,
            name = App.APP_LIE_DETECTOR_NAME
        )
        apps.add(appLieDetector)

        val appAIChat = App(
            id = App.APP_AI_CHAT_ID,
            logo = R.drawable.app_ai_chat_logo,
            name = App.APP_AI_CHAT_NAME
        )
        apps.add(appAIChat)

        val appWhatsappSticker = App(
            id = App.APP_WHATSAPP_STICKERS_ID,
            logo = R.drawable.app_sticker_whatsapp_logo_v2,
            name = App.APP_WHATSAPP_STICKERS_NAME
        )
        apps.add(appWhatsappSticker)

        val appPaint = App(
            id = App.APP_PAINT_ID,
            logo = R.drawable.app_paint_logo,
            name = App.APP_PAINT_NAME
        )
        apps.add(appPaint)

        val appImageFilters = App(
            id = App.APP_IMAGE_FILTERS_ID,
            logo = R.drawable.app_image_filters_logo,
            name = App.APP_IMAGE_FILTERS_NAME
        )
        apps.add(appImageFilters)

        return apps
    }
}