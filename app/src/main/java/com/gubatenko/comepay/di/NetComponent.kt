package com.gubatenko.comepay.di

import com.gubatenko.comepay.di.module.AppModule
import com.gubatenko.comepay.di.module.NetModule
import com.gubatenko.comepay.di.module.ResModule
import com.gubatenko.comepay.module.info.presenter.CityInfoPresenter
import com.gubatenko.comepay.module.list.presenter.CityPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class, ResModule::class))
interface NetComponent {

    fun inject(cityPresenter: CityPresenter)
    fun inject(cityPresenter: CityInfoPresenter)

}