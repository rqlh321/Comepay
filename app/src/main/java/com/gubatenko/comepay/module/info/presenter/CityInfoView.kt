package com.gubatenko.comepay.module.info.presenter

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.gubatenko.comepay.module.info.model.CityInfo

@StateStrategyType(AddToEndSingleStrategy::class)
interface CityInfoView : MvpView {

    fun success(item: CityInfo)

    fun error(message: String)

    fun progress(visibility: Int)

}