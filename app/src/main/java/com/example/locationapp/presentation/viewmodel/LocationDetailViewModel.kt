package com.example.locationapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.locationapp.domain.model.LocationDetail
import com.example.locationapp.domain.usecase.GetLocationDetails
import com.example.locationapp.exception.Failure

class LocationDetailViewModel(private val getLocationDetails: GetLocationDetails) : ViewModel() {

    private val _locationDetail = MutableLiveData<LocationDetail>()
    val locationDetail: LiveData<LocationDetail> = _locationDetail

    private val _failure: MutableLiveData<Failure> = MutableLiveData()
    val failure: LiveData<Failure> = _failure

    fun load(id: Int) = getLocationDetails(id, viewModelScope){it.fold(::handleFailure, ::handleLocationDetail)}

    private fun handleLocationDetail(locationDetail: LocationDetail){
        _locationDetail.postValue(locationDetail)
    }

    private fun handleFailure(failure: Failure) {
        _failure.value = failure
    }
}