package com.data.storages.storageModule.firebaseStorages.cloudStorage

import java.io.File

interface ICloudStorageHelper {
    fun initCloudStorage(storageBucket: String)

    fun uploadFiles(
        file: String,
        folderName: String?,
        uploadTypes: CloudStorageImplement.UploadTypes
    )

    fun uploadMediaFile(file: File,  folderName:String?)
}