package com.android.vncalling.base

import android.graphics.Bitmap
import android.util.Base64
import androidx.lifecycle.ViewModel
import com.android.vncalling.data.DataManager
import com.android.vncalling.data.DataManagerImplement
import com.android.vncalling.utilities.rx.SchedulerProvider
import com.android.vncalling.utilities.rx.SchedulerProviderImplement
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.io.ByteArrayOutputStream

/**
 * Author: William Nguyen | 6/9/2022
 * */
abstract class BaseViewModel constructor(
    private val dataManager: DataManager = DataManagerImplement(),
    private val schedulerProvider: SchedulerProvider = SchedulerProviderImplement(),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
) : ViewModel() {
    /**
     * return instance of dataManager
     * */
    fun getDataManger(): DataManager {
        return this.dataManager
    }

    /**
     * return instance of schedulerProvider
     * */
    fun getSchedulerProvider(): SchedulerProvider {
        return this.schedulerProvider
    }

    /**
     * return instance of compositeDisposable
     * */
    fun getCompositeDisposable(): CompositeDisposable {
        return this.compositeDisposable
    }

    /**
     * return instance of FirebaseFirestore
     * */
    fun getFirebaseFirestore(): FirebaseFirestore {
        return this.dataManager.getFirebaseFireStore()
    }

    /**
     * encode bitmap image to string type
     * */
    fun encodeBitmapImage(bitmap: Bitmap): String? {
        val previewWidth = 150
        val previewHeight = bitmap.height * previewWidth / bitmap.width
        val previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false)
        val byteArrayOutputStream = ByteArrayOutputStream()
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)
        val bytes = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }
}