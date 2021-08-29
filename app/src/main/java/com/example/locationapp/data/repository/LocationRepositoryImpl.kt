package com.example.locationapp.data.repository

import com.example.locationapp.data.api.LocationAppService
import com.example.locationapp.data.mapper.LocationApiToDomainMapper
import com.example.locationapp.data.mapper.LocationDetailApiToDomainMapper
import com.example.locationapp.domain.model.Location
import com.example.locationapp.domain.model.LocationDetail
import com.example.locationapp.domain.repository.LocationRepository
import com.example.locationapp.exception.Either
import com.example.locationapp.exception.Failure
import com.example.locationapp.utils.NetworkHandler

class LocationRepositoryImpl(
    private val networkHandler: NetworkHandler,
    private val api: LocationAppService
) : LocationRepository {
    private val mapperLocation: LocationApiToDomainMapper = LocationApiToDomainMapper()
    private val mapperLocationDetail: LocationDetailApiToDomainMapper = LocationDetailApiToDomainMapper()

    override suspend fun listAll(): Either<Failure, List<Location>> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> Either.Right(mapperLocation.map(api.listAll()))
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun getLocationDetail(id: Number): Either<Failure, LocationDetail> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> Either.Right(mapperLocationDetail.map(api.getLocationDetail(id)))
            false -> Either.Left(Failure.NetworkConnection)
        }
    }
}