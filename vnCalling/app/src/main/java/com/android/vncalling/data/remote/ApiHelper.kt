package com.android.vncalling.data.remote

import com.android.vncalling.data.remote.models.Model
import io.reactivex.rxjava3.core.Single

interface ApiHelper {
    fun getModels(q: String): Single<List<Model>>
}