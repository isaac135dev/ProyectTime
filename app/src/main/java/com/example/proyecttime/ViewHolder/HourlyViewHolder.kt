package com.example.proyecttime.ViewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecttime.databinding.ItemViewHourlyBinding
import com.squareup.picasso.Picasso

class HourlyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemViewHourlyBinding.bind(view)

    fun bind() {
//        binding.tvHour.text = result.hour
//        binding.tvTemp.text = result.temp.toString()
//        Picasso.get().load(result.picPath).into(binding.imStatus)
    }
}