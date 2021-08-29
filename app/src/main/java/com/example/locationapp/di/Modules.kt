package com.example.locationapp.di

import com.example.locationapp.data.api.LocationAppService
import com.example.locationapp.data.api.NetworkClient
import com.example.locationapp.data.repository.LocationRepositoryImpl
import com.example.locationapp.domain.repository.LocationRepository
import com.example.locationapp.domain.usecase.GetLocationDetails
import com.example.locationapp.domain.usecase.GetLocations
import com.example.locationapp.presentation.viewmodel.LocationDetailViewModel
import com.example.locationapp.presentation.viewmodel.LocationViewModel
import com.example.locationapp.utils.NetworkHandler
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val domainModule = module {
    factory { LocationRepositoryImpl(get(),api= get()) }
    factory { GetLocations(get()) }
    factory { GetLocationDetails(get()) }
}

val dataModules = module {
    factory<LocationRepository> { LocationRepositoryImpl(get(), api = get()) }
}

val networkModules = module {
    single { NetworkClient() }
    single{ NetworkHandler(get()) }
    factory { get<NetworkClient>().create(LocationAppService::class.java) }
}

val presentationModule = module {
    viewModel { LocationViewModel(get()) }
    viewModel { LocationDetailViewModel(get()) }
}