package com.gubatenko.comepay.data.local.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(WeatherDb::class), version = 1)

abstract class WeatherDataBase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}