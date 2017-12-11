package com.gubatenko.comepay.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.gubatenko.comepay.data.local.Res
import com.gubatenko.comepay.data.local.room.WeatherDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule {

    @Provides
    @Singleton
    internal fun provideRoomModule(application: Application): WeatherDataBase = Room.databaseBuilder(application, WeatherDataBase::class.java, "database-comepay").build()

}