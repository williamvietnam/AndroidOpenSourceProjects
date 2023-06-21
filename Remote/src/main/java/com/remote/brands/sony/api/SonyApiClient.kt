package com.remote.brands.sony.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SonyApiClient private constructor() {
    private val apiService: SonyApiServices

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val request: Request = chain.request()
                    .newBuilder()
                    .addHeader("X-Auth-PSK", SonyApiEndpoints.SONY_PRE_SHARED_KEY)
                    .build()
                chain.proceed(request)
            })
            .addInterceptor(httpLoggingInterceptor)
            .build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(SonyApiEndpoints.SONY_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        apiService = retrofit.create(SonyApiServices::class.java)
    }

    fun getApiService(): SonyApiServices {
        return apiService
    }

    companion object {
        var instance: SonyApiClient? = null
            get() {
                if (field == null) {
                    field = SonyApiClient()
                }
                return field
            }
            private set
    }
}