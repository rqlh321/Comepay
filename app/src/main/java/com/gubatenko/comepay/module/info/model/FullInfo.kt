package com.gubatenko.comepay.module.info.model

import com.google.gson.annotations.SerializedName

data class FullInfo(@SerializedName("fact") val fact: Fact ){
    companion object {
        data class Fact(@SerializedName("temp") val temp: Int)
    }
}