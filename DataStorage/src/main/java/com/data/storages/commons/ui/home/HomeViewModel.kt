package com.data.storages.commons.ui.home

import androidx.lifecycle.ViewModel
import com.data.storages.R

class HomeViewModel : ViewModel() {

    fun initializeHomeItems(): MutableList<HomeItem> {
        val items = ArrayList<HomeItem>()

        val cameraItem = HomeItem(
            HomeItem.ITEM_HOME_CAMERA_ID,
            "Take Photos & Videos",
            R.drawable.icon_camera,
            R.color.color_9FA8DA
        )
        items.add(cameraItem)

        val internalStorageItem = HomeItem(
            HomeItem.ITEM_HOME_INTERNAL_STORAGE_ID,
            "Internal Storage",
            R.drawable.icon_internal_storage,
            R.color.color_FFCDD2
        )
        items.add(internalStorageItem)

        val externalStorageItem = HomeItem(
            HomeItem.ITEM_HOME_EXTERNAL_STORAGE_ID,
            "External Storage",
            R.drawable.icon_external_storage,
            R.color.color_B3E5FC
        )
        items.add(externalStorageItem)

        val cloudFireStoreItem = HomeItem(
            HomeItem.ITEM_HOME_CLOUD_STORAGE_ID,
            "Cloud Firestore",
            R.drawable.icon_cloud_firestore,
            R.color.color_FFE082
        )
        items.add(cloudFireStoreItem)

        val realtimeDatabaseItem = HomeItem(
            HomeItem.ITEM_HOME_REALTIME_DATABASE_ID,
            "Realtime Database",
            R.drawable.icon_realtime_database,
            R.color.color_B39DDB
        )
        items.add(realtimeDatabaseItem)

        val roomDatabaseItem = HomeItem(
            HomeItem.ITEM_HOME_ROOM_DATABASE_ID,
            "Room Storage",
            R.drawable.icon_room_database,
            R.color.color_FFCCBC
        )
        items.add(roomDatabaseItem)

        return items
    }
}