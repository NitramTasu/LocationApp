package com.example.locationapp.domain.usecase

import com.example.locationapp.domain.model.LocationDetail
import com.example.locationapp.domain.repository.LocationRepository
import com.example.locationapp.exception.Either
import com.example.locationapp.exception.Failure

class GetLocationDetails(private val repository: LocationRepository) :
    UseCase<LocationDetail, Int>() {
    override suspend fun run(params: Int): Either<Failure, LocationDetail> =
        repository.getLocationDetail(params)
}