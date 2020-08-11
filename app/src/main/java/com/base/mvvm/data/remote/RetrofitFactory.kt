package com.base.mvvm.data.remote

import com.base.mvvm.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory private constructor() {
    val remoteAPI: RemoteAPI

    companion object {
        private const val READ_TIMEOUT_SECONDS = 60
        private const val CONNECT_TIMEOUT_SECONDS = 60
        var instance: RetrofitFactory? = null
            get() {
                if (field == null) {
                    field = RetrofitFactory()
                }
                return field
            }
            private set
    }

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val clientBuilder = OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
                .addInterceptor(interceptor)

        clientBuilder.addInterceptor(fun(chain: Interceptor.Chain): Response? {
            val request: Request = chain.request().newBuilder()
                    .build()
            return chain.proceed(request)
        })
        val httpClient = clientBuilder.build()
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()
        remoteAPI = retrofit.create(RemoteAPI::class.java)
    }
}