package com.example.locationapp.domain.usecase

import com.example.locationapp.domain.model.Location
import com.example.locationapp.domain.repository.LocationRepository

class GetLocations(private val locationRepository: LocationRepository) :
    UseCase<List<Location>, UseCase.None>() {
    override suspend fun run(params: None) = locationRepository.listAll()
}