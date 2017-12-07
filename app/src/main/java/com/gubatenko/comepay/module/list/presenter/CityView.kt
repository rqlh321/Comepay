package com.gubatenko.comepay.module.list.presenter

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.gubatenko.comepay.module.list.model.City

@StateStrategyType(AddToEndSingleStrategy::class)
interface CityView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun show(item: City)

    fun success(list: List<City>)

    fun error(message: String)

    fun progress(visibility: Int)

}