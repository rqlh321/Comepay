package com.gubatenko.comepay.module.info.model

import com.google.gson.annotations.SerializedName

data class Day(@SerializedName("main") val main: Main,
               @SerializedName("weather") val weather: List<Condition>) {
    data class Condition(@SerializedName("description") val description: String)

    data class Main(@SerializedName("temp") val temp: Float)
}