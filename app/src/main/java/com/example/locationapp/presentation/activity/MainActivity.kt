package com.example.locationapp.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.locationapp.R
import com.example.locationapp.domain.model.Location
import com.example.locationapp.exception.Failure
import com.example.locationapp.presentation.adapter.LocationAdapter
import com.example.locationapp.presentation.viewmodel.LocationViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val locationViewModel: LocationViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Home"

        local_list.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        locationViewModel.location.observe(this, ::renderLocationList)
        locationViewModel.failure.observe(this, ::handleFailure)
        locationViewModel.load()
    }

    private fun renderLocationList(locations: List<Location>) {
        local_list.adapter = LocationAdapter(locations)
    }

    private fun handleFailure(failure: Failure?) {
        val parentLayout = findViewById<View>(android.R.id.content)
        when (failure) {
            is Failure.NetworkConnection -> Snackbar.make(parentLayout, getString(R.string.connection_error_message) , Snackbar.LENGTH_SHORT)
                .show()
        }
    }
}