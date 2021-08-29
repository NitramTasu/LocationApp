package com.example.locationapp.presentation.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.locationapp.R
import com.example.locationapp.domain.model.Location
import com.example.locationapp.presentation.activity.LocationDetailActivity

class LocationAdapter(
    private val locationList: List<Location>
) :
    RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name_tv: TextView = view.findViewById(R.id.name_tv)
        val description_tv: TextView = view.findViewById(R.id.description_tv)
        val ratingBar: RatingBar = view.findViewById(R.id.ratingBar)
        val rating_number_tv: TextView = view.findViewById(R.id.rating_number_tv)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.local_item, viewGroup, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(locationList[position]) {
            viewHolder.name_tv.text = locationList[position].name
            viewHolder.description_tv.text = locationList[position].type
            viewHolder.rating_number_tv.text = locationList[position].review.toString()
            viewHolder.ratingBar.rating = locationList[position].review
            viewHolder.itemView.setOnClickListener {
                val intent = Intent(viewHolder.itemView.context, LocationDetailActivity::class.java)
                intent.putExtra("locationId", locationList[position].id)
                viewHolder.itemView.context.startActivity(intent)
            }
            //Glide.with(viewHolder.context).load(locationList[position].localAvatar).into(viewHolder.localIv);
        }

    }

    override fun getItemCount() = locationList.size

}