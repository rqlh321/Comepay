package com.gubatenko.comepay.data.local.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "WEATHER")
data class WeatherDb(@PrimaryKey var cityId: Int = 0,
                     var temp: Float = 0f,
                     var description: String = "",
                     var date: Long = 0)
