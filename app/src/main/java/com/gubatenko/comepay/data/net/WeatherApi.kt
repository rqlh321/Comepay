package com.gubatenko.comepay.data.net

import com.gubatenko.comepay.module.info.model.Day
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface WeatherApi {

    @GET("2.5/weather/?units=metric")
    fun info(@Query("id") id: Int): Single<Day>

    @GET
    fun picture(@Url url: String): Single<ResponseBody>
}