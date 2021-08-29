package com.example.locationapp.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LocationApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LocationApp)
            modules(domainModule)
            modules(dataModules)
            modules(networkModules)
            modules(presentationModule)
        }
    }
}