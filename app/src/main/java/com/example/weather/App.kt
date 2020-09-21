package com.example.weather

import android.app.Application
import android.content.Context
import com.example.weather.di.component.AppComponent
import com.example.weather.di.component.DaggerAppComponent
import com.example.weather.di.module.AppModule
import com.facebook.stetho.Stetho
import com.google.android.libraries.places.api.Places
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber

class App : Application() {

    companion object {
        private lateinit var appContext: Context
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        setupLogger()
        configureDagger()
        configureStetho()
        RxJavaPlugins.setErrorHandler {}
        Places.initialize(this, getString(R.string.google_api_key))

    }

    private fun setupLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }


    private fun configureDagger(){
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        App.appComponent.inject(this)

    }

    private fun configureStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .build())
        }
    }

}
