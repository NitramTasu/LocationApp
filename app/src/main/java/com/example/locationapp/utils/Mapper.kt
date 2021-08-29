package com.example.locationapp.utils

interface Mapper<S, T> {
    fun map(source: S): T
}