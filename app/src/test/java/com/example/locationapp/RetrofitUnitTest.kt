package com.example.locationapp

import com.example.locationapp.data.api.LocationAppService
import com.example.locationapp.data.api.NetworkClient
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RetrofitUnitTest {

    @Test
    fun `test`()= runBlocking {

        val result = NetworkClient().create(LocationAppService::class.java).getLocationDetail(1)


    }
}