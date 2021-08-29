package com.example.locationapp.data.api

import com.example.locationapp.data.model.LocationDetailResponse
import com.example.locationapp.data.model.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationAppService {
    @GET("/locations")
    suspend fun listAll():LocationResponse

    @GET("/locations/{id}")
    suspend fun getLocationDetail(@Path("id") id: Number): LocationDetailResponse
}