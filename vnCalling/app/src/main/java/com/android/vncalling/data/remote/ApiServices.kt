package com.android.vncalling.data.remote

import com.android.vncalling.data.remote.models.Model
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET(ApiEndpoint.GET_MODEL)
    fun getModels(@Query("query") q: String): Single<List<Model>>
}