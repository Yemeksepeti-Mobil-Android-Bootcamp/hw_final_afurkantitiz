package com.afurkantitiz.foodapp.ui.restaurants.listrestaurant

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.data.entity.Restaurant
import com.afurkantitiz.foodapp.databinding.ItemAllRestaurantCardBinding
import com.bumptech.glide.Glide

class RestaurantsAdapter(private val allRestaurantsList: ArrayList<Restaurant>, private val mContext: Context)
    : RecyclerView.Adapter<RestaurantsAdapter.AllRestaurantsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllRestaurantsViewHolder {
        val binding = ItemAllRestaurantCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllRestaurantsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllRestaurantsViewHolder, position: Int) {
        val allRestaurant: Restaurant = allRestaurantsList[position]

        holder.binding.itemAllRestaurantNameText.text = allRestaurant.restaurantName
        holder.binding.itemAllRestaurantMealCount.text = allRestaurant.mealCount.toString()

        Glide
            .with(mContext)
            .load(allRestaurant.restaurantImage)
            .into(holder.binding.itemAllRestaurantImageView)

        holder.binding.itemAllRestaurantsCardView.setOnClickListener {
            it.findNavController().navigate(R.id.action_listRestaurantsFragment_to_detailRestaurantFragment)
        }
    }

    override fun getItemCount(): Int = allRestaurantsList.size

    inner class AllRestaurantsViewHolder(val binding: ItemAllRestaurantCardBinding): RecyclerView.ViewHolder(binding.root)
}