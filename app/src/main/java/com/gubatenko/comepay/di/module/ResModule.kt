package com.gubatenko.comepay.di.module

import android.app.Application
import com.gubatenko.comepay.data.local.Res
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ResModule {

    @Provides
    @Singleton
    internal fun provideRes(application: Application): Res = Res(application)

}