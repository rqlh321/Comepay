package com.gubatenko.comepay

import android.app.Application
import com.gubatenko.comepay.di.DaggerNetComponent
import com.gubatenko.comepay.di.NetComponent
import com.gubatenko.comepay.di.module.AppModule
import com.gubatenko.comepay.di.module.NetModule
import com.gubatenko.comepay.di.module.ResModule
import com.gubatenko.comepay.di.module.RoomModule
import io.reactivex.internal.functions.Functions
import io.reactivex.plugins.RxJavaPlugins

class CompeyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        netComponent = DaggerNetComponent
                .builder()
                .appModule(AppModule(this))
                .resModule(ResModule())
                .netModule(NetModule(getString(R.string.server), getString(R.string.api_key)))
                .roomModule(RoomModule())
                .build()

        RxJavaPlugins.setErrorHandler(Functions.emptyConsumer())
    }

    companion object {

        private lateinit var netComponent: NetComponent
        fun netComponent(): NetComponent = netComponent

    }

}