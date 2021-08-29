package com.example.locationapp.domain.repository

import com.example.locationapp.domain.model.Location
import com.example.locationapp.domain.model.LocationDetail
import com.example.locationapp.exception.Either
import com.example.locationapp.exception.Failure

interface LocationRepository {
    suspend fun listAll(): Either<Failure, List<Location>>
    suspend fun getLocationDetail(id:Number): Either<Failure, LocationDetail>
}