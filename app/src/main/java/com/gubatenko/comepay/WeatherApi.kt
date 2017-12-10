package com.gubatenko.comepay

import com.gubatenko.comepay.module.info.model.FullInfo
import com.gubatenko.comepay.module.list.model.City
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("locations")
    fun cites(): Single<List<City>>

    @GET("forecast/{geoid}")
    fun info(@Query("geoid") id: Int): Single<FullInfo>
}