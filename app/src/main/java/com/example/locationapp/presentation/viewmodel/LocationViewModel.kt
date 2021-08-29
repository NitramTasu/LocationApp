package com.example.locationapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.locationapp.domain.model.Location
import com.example.locationapp.domain.usecase.GetLocations
import com.example.locationapp.domain.usecase.UseCase
import com.example.locationapp.exception.Failure

class LocationViewModel(val getLocations: GetLocations) : ViewModel() {

    private val _locations = MutableLiveData<List<Location>>()
    val location: LiveData<List<Location>> = _locations

    private val _failure: MutableLiveData<Failure> = MutableLiveData()
    val failure: LiveData<Failure> = _failure

    fun load() = getLocations(UseCase.None(), viewModelScope) {
        it.fold(
            ::handleFailure,
            ::handleLocationDetail
        )
    }

    private fun handleLocationDetail(locations: List<Location>) {
        _locations.postValue(locations)
    }

    private fun handleFailure(failure: Failure) {
        _failure.value = failure
    }
}