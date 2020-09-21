package com.example.weather.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface BaseView: MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showProgress()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showProgress(message: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showProgress(textResId: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun hideProgress()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showNoInternetConnectionError()

}