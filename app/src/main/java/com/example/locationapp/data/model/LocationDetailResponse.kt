package com.example.locationapp.data.model

import com.google.gson.annotations.SerializedName

class LocationDetailResponse (@SerializedName("id")val id: Int,
                              @SerializedName("name")val name: String,
                              @SerializedName("review")val review: Float,
                              @SerializedName("type")val type: String,
                              @SerializedName("about")val about: String,
                              @SerializedName("phone")val phone: String,
                              @SerializedName("adress")val address: String,
                              @SerializedName("schedule")val schedule: Schedule
)