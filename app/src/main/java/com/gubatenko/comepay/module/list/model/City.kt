package com.gubatenko.comepay.module.list.model

import com.google.gson.annotations.SerializedName

data class City(@SerializedName("geoid") val id: Int,
                @SerializedName("name") val name: String)