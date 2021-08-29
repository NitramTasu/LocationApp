package com.example.locationapp.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
//import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://hotmart-mobile-app.herokuapp.com/"

class NetworkClient {
    private val gson: Gson by lazy { GsonBuilder().create() }
    fun <T> create(service: Class<T>): T {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .baseUrl(BASE_URL).build()
            .create(service)
    }
}