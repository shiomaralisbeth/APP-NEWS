package com.shiomara.appnewsdata.di

import com.shiomara.appnews.data.R
import com.shiomara.appnews.data.network.RestApi
import com.shiomara.appnewsdata.utils.LocalStorage
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single { createHttpClient(get()) }
    single { createWebService<RestApi>(get(), androidContext().getString(R.string.api_url)) }
}

fun createHttpClient(localStorage: LocalStorage): OkHttpClient {
    val httpClientBuilder = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)

    /*if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClientBuilder.addInterceptor(loggingInterceptor)
    }

    httpClientBuilder.addInterceptor(AuthInterceptor(localStorage))*/

    return httpClientBuilder.build()
}

inline fun <reified T> createWebService(httpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}