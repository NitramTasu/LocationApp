package com.example.locationapp.presentation.activity

import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.locationapp.R
import com.example.locationapp.domain.model.LocationDetail
import com.example.locationapp.exception.Failure
import com.google.gson.Gson
import com.example.locationapp.presentation.viewmodel.LocationDetailViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.gson.reflect.TypeToken
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

class LocationDetailActivity : AppCompatActivity() {
    private var weekMap: HashMap<String, String>? = null
    private val locationDetailViewModel: LocationDetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_detail)

        weekMap = Gson().fromJson(
            getString(R.string.week_map),
            object : TypeToken<HashMap<String, String>>() {}.type
        )

        locationDetailViewModel.locationDetail.observe(this, ::renderLocationDetail)
        locationDetailViewModel.failure.observe(this, ::handleFailure)
    }

    override fun onResume() {
        super.onResume()
        val locationId = intent.extras?.let {
            it.getInt("locationId")
        } ?: 1
        locationDetailViewModel.load(locationId)
    }
    private fun renderLocationDetail(locationDetail: LocationDetail?) {
        findViewById<TextView>(R.id.name_tv).apply {
            text = locationDetail?.name
        }
        findViewById<RatingBar>(R.id.rating_bar).apply {
            rating = locationDetail?.review!!
        }
        findViewById<TextView>(R.id.rating_number_tv).apply {
            text = locationDetail?.review.toString()
        }
        findViewById<TextView>(R.id.description_tv).apply {
            if (locationDetail != null) {
                text = locationDetail.about
            }
        }
        findViewById<TextView>(R.id.time_descrition_tv).apply {
            var builderString = StringBuilder()
            if (locationDetail != null) {
                for (schedule in locationDetail.days) {
                    builderString.append(weekMap?.get(schedule.dayOfWeek)).append(": ")
                        .append(schedule.day.open).append(" ás ").append(schedule.day.close)
                        .append("\n")
                }
            }
            text = builderString.toString()
        }
        findViewById<TextView>(R.id.phone_tv).apply {
            if (locationDetail != null) {
                text = locationDetail.phone
            }
        }
        findViewById<TextView>(R.id.address_tv).apply {
            if (locationDetail != null) {
                text = locationDetail.address
            }
        }
    }
    private fun handleFailure(failure: Failure?) {
        val parentLayout = findViewById<View>(android.R.id.content)
        when (failure) {
            is Failure.NetworkConnection -> Snackbar.make(parentLayout, getString(R.string.connection_error_message) , Snackbar.LENGTH_SHORT)
                .show()
        }
    }
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    @Suppress("DEPRECATION")
    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}



