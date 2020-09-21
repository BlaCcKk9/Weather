package com.example.weather.presenter

import android.content.Context
import com.example.weather.App
import com.example.weather.network.RestApi
import javax.inject.Inject

class UtilityWrapper {
    @Inject
    lateinit var applicationContext: Context

    @Inject
    lateinit var restApi: RestApi

    init {
        App.appComponent.inject(this)
    }

}