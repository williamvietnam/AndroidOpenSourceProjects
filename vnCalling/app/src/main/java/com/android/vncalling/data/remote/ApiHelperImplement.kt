package com.android.vncalling.data.remote

import com.android.vncalling.data.remote.models.Model
import io.reactivex.rxjava3.core.Single

class ApiHelperImplement(private val apiServices: ApiServices) : ApiHelper {

    override fun getModels(q: String): Single<List<Model>> = apiServices.getModels(q)

}