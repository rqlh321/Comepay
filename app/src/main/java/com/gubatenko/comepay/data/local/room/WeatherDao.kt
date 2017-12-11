package com.gubatenko.comepay.data.local.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: WeatherDb)

    @Query("SELECT * FROM WEATHER WHERE cityId=:arg0")
    fun get(cityId: Int): Single<List<WeatherDb>>

}