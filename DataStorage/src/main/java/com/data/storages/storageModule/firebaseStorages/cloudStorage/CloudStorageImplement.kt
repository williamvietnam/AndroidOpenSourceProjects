package com.data.storages.storageModule.firebaseStorages.cloudStorage

import android.net.Uri
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.io.File

class CloudStorageImplement : ICloudStorageHelper {

    companion object {
        private const val TAG = "CloudStorageImplement"
    }

    enum class UploadTypes {
        BYTES, STREAM, FILE
    }

    private var storage: FirebaseStorage? = null
    private var storageRef: StorageReference? = null

    override fun initCloudStorage(storageBucket: String) {
        this.storage = Firebase.storage(storageBucket)
        this.storageRef = storage?.reference
    }

    override fun uploadFiles(file: String, folderName: String?, uploadTypes: UploadTypes) {
        val mountainFilesRef: StorageReference? = if (folderName == null) {
            storageRef?.child(file)
        } else {
            storageRef?.child("${folderName}/${file}")
        }

        when (uploadTypes.name) {
            UploadTypes.BYTES.name -> {
                Log.d(TAG, "")
            }

            UploadTypes.STREAM.name -> {

            }

            UploadTypes.FILE.name -> {

            }
        }
    }

    // upload photo or video by putFile() method
    override fun uploadMediaFile(file: File, folderName: String?) {
        val fileUri = Uri.fromFile(file)
        val riverRef = storageRef?.child("${folderName}/${fileUri.lastPathSegment}")
        val uploadTask = riverRef?.putFile(fileUri)
        uploadTask?.addOnCanceledListener {
            Log.d(TAG, "uploadPhotoFile -> uploadTask SUCCESS")
        }?.addOnFailureListener {
            Log.d(TAG, "uploadPhotoFile -> uploadTask FAILURE: ${it.message} ")
        }
    }


}