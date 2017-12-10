package com.gubatenko.comepay.module.info.presenter

import android.graphics.Bitmap
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.gubatenko.comepay.CompeyApplication
import com.gubatenko.comepay.WeatherApi
import com.gubatenko.comepay.module.info.model.CityInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class CityInfoPresenter(private val id: Int?,private val name: String?) : MvpPresenter<CityInfoView>() {
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
                    .map {
                        val picture = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_4444)
                        CityInfo(picture, "${it.fact} $name")
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ viewState.success(it) }, { viewState.error(it.message.toString()) })

    }
}