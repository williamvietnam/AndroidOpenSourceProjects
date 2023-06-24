package com.android.container.appsMenu

import com.android.R
import com.android.commons.base.BaseViewModel

class AppsMenuViewModel : BaseViewModel() {
    fun createApps(): MutableList<App> {
        val apps = ArrayList<App>()

        val appCamera = App(
            id = App.APP_CAMERA_ID,
            logo = R.drawable.app_camera_logo,
            name = App.APP_CAMERA_NAME
        )
        apps.add(appCamera)

        val appStepCounter = App(
            id = App.APP_STEP_COUNTER_ID,
            logo = R.drawable.app_step_counter_logo,
            name = App.APP_STEP_COUNTER_NAME
        )
        apps.add(appStepCounter)

        val appSecretPhotos = App(
            id = App.APP_SECRET_PHOTOS_ID,
            logo = R.drawable.app_secret_photos_logo,
            name = App.APP_SECRET_PHOTOS_NAME
        )
        apps.add(appSecretPhotos)

        val appPrankSound = App(
            id = App.APP_PRANK_SOUND_ID,
            logo = R.drawable.app_prank_sound_logo,
            name = App.APP_PRANK_SOUND_NAME
        )
        apps.add(appPrankSound)

        val appWeather = App(
            id = App.APP_WEATHER_ID,
            logo = R.drawable.app_weather_logo,
            name = App.APP_WEATHER_NAME
        )
        apps.add(appWeather)

        val appFakeCall = App(
            id = App.APP_FAKE_CALL_ID,
            logo = R.drawable.app_fake_call_logo,
            name = App.APP_FAKE_CALL_NAME
        )
        apps.add(appFakeCall)

        val appAlarm = App(
            id = App.APP_ALARM_ID,
            logo = R.drawable.app_alarm_logo,
            name = App.APP_ALARM_NAME
        )
        apps.add(appAlarm)

        val appLieDetector = App(
            id = App.APP_LIE_DETECTOR_ID,
            logo = R.drawable.app_lie_detector_logo,
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
            logo = R.drawable.app_sticker_whatsapp_logo,
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

        val appWallpaper = App(
            id = App.APP_WALLPAPER_ID,
            logo = R.drawable.app_wallpaper_logo,
            name = App.APP_WALLPAPER_NAME
        )
        apps.add(appWallpaper)

        val appCharts = App(
            id = App.APP_CHARTS_ID,
            logo = R.drawable.app_chart_logo,
            name = App.APP_CHARTS_NAME
        )
        apps.add(appCharts)

        val appVideoShorts = App(
            id = App.APP_SHORTS_ID,
            logo = R.drawable.app_video_shorts_logo,
            name = App.APP_SHORTS_NAME
        )
        apps.add(appVideoShorts)

        return apps
    }
}