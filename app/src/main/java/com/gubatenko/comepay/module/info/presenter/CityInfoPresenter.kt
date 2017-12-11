package com.gubatenko.comepay.module.info.presenter

import android.view.View
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.gubatenko.comepay.CompeyApplication
import com.gubatenko.comepay.data.local.Res
import com.gubatenko.comepay.data.local.room.WeatherDataBase
import com.gubatenko.comepay.data.local.room.WeatherDb
import com.gubatenko.comepay.data.net.WeatherApi
import com.gubatenko.comepay.module.info.model.CityInfo
import com.gubatenko.comepay.module.info.model.Day
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class CityInfoPresenter(private val id: Int?) : MvpPresenter<CityInfoView>() {
    init {
        CompeyApplication.netComponent().inject(this)
    }

    @Inject lateinit var weatherNet: WeatherApi
    @Inject lateinit var weatherLocal: WeatherDataBase
    @Inject lateinit var res: Res

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        content()
    }

    fun content() {
        if (id != null) weatherLocal.weatherDao().get(id)
                .flatMap {
                    if (it.isEmpty() || it.isNotEmpty() && isHourPass(it.first())) weatherNet.info(id)
                            .map {
                                weatherLocal.weatherDao().insert(WeatherDb(id, it.main.temp, it.weather.first().description, System.currentTimeMillis()))
                                it
                            }
                    else Single.just(Day(Day.Main(it.first().temp), listOf(Day.Condition(it.first().description))))
                }
                .flatMap { day ->
                    Single.fromCallable {
                        res.picture(day)
                    }
                            .onErrorReturnItem(res.error())
                            .map {
                                CityInfo(it, "${day.main.temp}, ${day.weather.first().description}")
                            }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.progress(View.VISIBLE) }
                .doOnError { viewState.progress(View.GONE) }
                .doOnSuccess { viewState.progress(View.GONE) }
                .subscribe({ viewState.success(it) }, { viewState.error(it.message.toString()) })
    }

    private fun isHourPass(it: WeatherDb): Boolean {
        return System.currentTimeMillis() - it.date > 60 * 60 * 1000
    }


}