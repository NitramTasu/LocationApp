package com.example.locationapp.data.model

import com.google.gson.annotations.SerializedName

data class Schedule (
    @SerializedName("monday") val monday: DayApi,
    @SerializedName("tuesday") val tuesday: DayApi,
    @SerializedName("wednesday") val wednesday: DayApi,
    @SerializedName("thursday") val thursday: DayApi,
    @SerializedName("friday") val friday: DayApi,
    @SerializedName("saturday") val saturday: DayApi,
    @SerializedName("sunday") val sunday: DayApi
)