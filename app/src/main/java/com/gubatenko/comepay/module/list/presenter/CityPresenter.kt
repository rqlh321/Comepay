package com.gubatenko.comepay.module.list.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.gubatenko.comepay.CompeyApplication
import com.gubatenko.comepay.WeatherApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class CityPresenter : MvpPresenter<CityView>() {
    init {
        CompeyApplication.netComponent().inject(this)
    }

    @Inject lateinit var weather: WeatherApi

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        content()
    }

    private fun content() {
        weather.cites()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ viewState.success(it) }, { viewState.error(it.message.toString()) })
    }
}