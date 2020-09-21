package com.example.weather.presenter

import com.arellomobile.mvp.MvpPresenter
import com.example.weather.view.BaseView
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<T : BaseView> : MvpPresenter<T>() {
    private var utilityWrapper: UtilityWrapper = UtilityWrapper()

    val subscription: CompositeDisposable = CompositeDisposable()

    fun restApi() = utilityWrapper.restApi

    fun applicationContext() = utilityWrapper.applicationContext

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    override fun onDestroy() {
        subscription.dispose()
        super.onDestroy()
    }


}