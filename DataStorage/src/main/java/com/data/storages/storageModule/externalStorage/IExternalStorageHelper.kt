package com.data.storages.storageModule.externalStorage

interface IExternalStorageHelper {
    // init variable need and declare permission
    fun initExternalStorage()

    fun saveDataToExternalStorage()

    fun getDataFromExternalStorage()
}