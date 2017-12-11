package com.gubatenko.comepay.module.info.presenter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.gubatenko.comepay.CompeyApplication
import com.gubatenko.comepay.data.net.WeatherApi
import com.gubatenko.comepay.module.info.model.CityInfo
import com.gubatenko.comepay.module.info.model.Day
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class CityInfoPresenter(private val id: Int?) : MvpPresenter<CityInfoView>() {
    init {
        CompeyApplication.netComponent().inject(this)
    }

    @Inject lateinit var weather: WeatherApi

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        content()
    }

    private fun content() {
        if (id != null) weather.info(id)
                .observeOn(Schedulers.newThread())
                .flatMap { day ->
                    weather.picture(picture(day))
                            .map {
                                val bm = BitmapFactory.decodeStream(it.byteStream())
                                CityInfo(Bitmap.createScaledBitmap(bm, 300, 300, false),
                                        "${day.main.temp}, ${day.weather.first().description}")
                            }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.progress(View.VISIBLE) }
                .doOnError { viewState.progress(View.GONE) }
                .doOnSuccess { viewState.progress(View.GONE) }
                .subscribe({ viewState.success(it) }, { viewState.error(it.message.toString()) })
    }

    private fun picture(day: Day) =
            if (day.main.temp <= 0) "https://hikingartist.files.wordpress.com/2012/05/1-christmas-tree.jpg"
            else "http://assets.smoothradio.com/2013/30/weather-1375260252-article-1.jpg"

}