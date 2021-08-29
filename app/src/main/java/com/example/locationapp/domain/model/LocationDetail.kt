package com.example.locationapp.domain.model

class LocationDetail(
    val id: Int,
    val name: String,
    val review: Float,
    val type: String,
    val about: String,
    val phone: String,
    val address: String,
    val days: List<Schedule>
)