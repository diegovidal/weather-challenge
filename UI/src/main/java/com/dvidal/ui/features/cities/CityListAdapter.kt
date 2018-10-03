package com.dvidal.ui.features.cities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dvidal.ui.R
import com.dvidal.ui.model.City
import kotlinx.android.synthetic.main.item_city.view.*
import javax.inject.Inject

/**
 * @author diegovidal on 02/10/2018.
 */
class CityListAdapter constructor(
        val onItemSelected: (cit: City) -> Unit
): RecyclerView.Adapter<CityListAdapter.ViewHolder>() {

    var cities: List<City> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_city, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cities.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = cities[position]
        holder.bindView(city)
    }

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bindView(city: City) {
            view.tv_city_name.text = city.name

            view.container.setOnClickListener {
                onItemSelected(city)
            }
        }
    }
}