package com.example.proyecttime.adatper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecttime.R
import com.example.proyecttime.ViewHolder.HourlyViewHolder

class HourlyAdapter() : RecyclerView.Adapter<HourlyViewHolder>() {
//
//    fun updateList(items: List<Hourly>) {
//        this.items = items
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        return HourlyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_hourly, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
//        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}