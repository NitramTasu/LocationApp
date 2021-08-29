package com.example.locationapp.data.mapper

import com.example.locationapp.data.model.LocationResponse
import com.example.locationapp.domain.model.Location
import com.example.locationapp.utils.Mapper

class LocationApiToDomainMapper : Mapper<LocationResponse, List<Location>> {
    override fun map(source: LocationResponse): List<Location> {
        val locationList = ArrayList<Location>()
        for (locationSouce in source.listLocations) {
            locationList.add(
                Location(
                    locationSouce.id,
                    locationSouce.name,
                    locationSouce.review,
                    locationSouce.type
                )
            )
        }
        return locationList
    }
}