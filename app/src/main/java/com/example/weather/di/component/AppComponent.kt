package com.example.weather.di.component

import com.example.weather.App
import com.example.weather.di.module.AppModule
import com.example.weather.presenter.UtilityWrapper
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: App)
    fun inject(utilityWrapper: UtilityWrapper)
}