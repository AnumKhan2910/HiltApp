package com.example.mymvvmapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymvvmapplication.R
import com.example.mymvvmapplication.model.CovidDataModel

class MyAdapter(var context: Context) : ListAdapter<CovidDataModel, RecyclerView.ViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_city, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CityViewHolder){
            holder.cityText.text = currentList[position].name
        }
    }


    class CityViewHolder(var view : View) : RecyclerView.ViewHolder(view) {
        val cityText: TextView = view.findViewById<TextView>(R.id.idCity)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }


    /*
   Using diffutil for efficient views update
   */
    class DiffCallBack : DiffUtil.ItemCallback<CovidDataModel>() {

        override fun areItemsTheSame(oldItem: CovidDataModel, newItem: CovidDataModel): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: CovidDataModel, newItem: CovidDataModel): Boolean {
            return oldItem == newItem
        }
    }
}


