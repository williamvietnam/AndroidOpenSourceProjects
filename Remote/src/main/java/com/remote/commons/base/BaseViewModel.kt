package com.remote.commons.base

import androidx.lifecycle.ViewModel
import com.remote.brands.sony.api.ISonyApiHelper
import com.remote.brands.sony.api.SonyApiImplement
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val sonyApis: ISonyApiHelper = SonyApiImplement()

    open fun getCompositeDisposable(): CompositeDisposable {
        return compositeDisposable
    }

    fun getSonyApis(): ISonyApiHelper {
        return this.sonyApis
    }
}