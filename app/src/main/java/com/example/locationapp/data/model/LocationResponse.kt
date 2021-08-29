package com.example.locationapp.data.model

import com.example.locationapp.domain.model.Location
import com.google.gson.annotations.SerializedName

class LocationResponse(
    @SerializedName("listLocations") val listLocations: List<Location>
)