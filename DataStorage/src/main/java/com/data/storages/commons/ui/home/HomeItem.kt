package com.data.storages.commons.ui.home

data class HomeItem(
    var id: String,

    var functionName: String,

    var functionIcon: Int,

    var backgroundColor: Int
) {
    companion object {
        const val ITEM_HOME_CAMERA_ID = "item.home.camera.id"
        const val ITEM_HOME_INTERNAL_STORAGE_ID = "item.home.internal.storage.id"
        const val ITEM_HOME_EXTERNAL_STORAGE_ID = "item.home.external.storage.id"
        const val ITEM_HOME_CLOUD_STORAGE_ID = "item.home.cloud.storage.id"
        const val ITEM_HOME_REALTIME_DATABASE_ID = "item.home.realtime.database.id"
        const val ITEM_HOME_ROOM_DATABASE_ID = "item.home.room.database.id"
    }
}