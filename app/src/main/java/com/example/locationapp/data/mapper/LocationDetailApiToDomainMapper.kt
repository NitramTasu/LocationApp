package com.example.locationapp.data.mapper

import com.example.locationapp.data.model.DayApi
import com.example.locationapp.data.model.LocationDetailResponse
import com.example.locationapp.domain.model.LocationDetail
import com.example.locationapp.data.model.Schedule
import com.example.locationapp.domain.model.Day
import com.example.locationapp.utils.Mapper
import kotlin.reflect.full.memberProperties

class LocationDetailApiToDomainMapper : Mapper<LocationDetailResponse, LocationDetail> {
    override fun map(source: LocationDetailResponse): LocationDetail {
        val schedule = ArrayList<com.example.locationapp.domain.model.Schedule>()

        for (prop in Schedule::class.memberProperties) {
            println("${prop.name} = ${prop.get(source.schedule)}")
            val propValue = prop.get(source.schedule)
            propValue?.let {
                var dayOfWeek: DayApi = it as DayApi
                schedule.add(
                    com.example.locationapp.domain.model.Schedule(
                        dayOfWeek = prop.name,
                        Day(dayOfWeek.open, dayOfWeek.close)
                    )
                )
            }
        }

        return LocationDetail(
            source.id,
            source.name,
            source.review,
            source.type,
            source.about,
            source.phone,
            source.address,
            schedule
        )
    }
}