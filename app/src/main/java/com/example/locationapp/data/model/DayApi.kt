package com.example.locationapp.data.model

import com.google.gson.annotations.SerializedName

data class DayApi(
    @SerializedName("open") val open: String = "",
    @SerializedName("close") val close: String = ""
)