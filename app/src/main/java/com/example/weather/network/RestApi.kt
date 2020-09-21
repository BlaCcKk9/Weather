package com.example.weather.network

import android.content.Context
import com.example.weather.Consts
import com.example.weather.network.services.WeatherService
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RestApi @Inject constructor(context: Context) {

    private lateinit var retrofit: Retrofit
    lateinit var weatherService: WeatherService

    init {
        invalidate()
    }

    private fun invalidate() {
        initRetrofit()
        initServices()
    }

    private fun initRetrofit() {
        val loggingInterceptor = HttpLoggingInterceptor { it -> Timber.tag(Consts.LogTag.HTTP).d(it) }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addNetworkInterceptor(StethoInterceptor())
            .build()

        retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("api.openweathermap.org/")
            .build()
    }

    private fun initServices() {
        weatherService = retrofit.create(WeatherService::class.java)
    }




}