package com.android.vncalling.base

import androidx.lifecycle.ViewModel
import com.android.vncalling.data.DataManager
import com.android.vncalling.data.DataManagerImplement
import com.android.vncalling.utils.rx.SchedulerProvider
import com.android.vncalling.utils.rx.SchedulerProviderImplement
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel constructor(
    private val dataManager: DataManager = DataManagerImplement(),
    private val schedulerProvider: SchedulerProvider = SchedulerProviderImplement(),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModel() {

    fun getDataManger(): DataManager {
        return this.dataManager
    }

    fun getSchedulerProvider(): SchedulerProvider {
        return this.schedulerProvider
    }

    fun getCompositeDisposable(): CompositeDisposable {
        return this.compositeDisposable
    }
}