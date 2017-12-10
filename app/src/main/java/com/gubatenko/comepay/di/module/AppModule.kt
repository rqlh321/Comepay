package com.gubatenko.comepay.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private var mApplication: Application) {

    @Provides
    @Singleton
    internal fun providesApplication(): Application = mApplication

}