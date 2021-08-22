package com.afurkantitiz.foodapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.afurkantitiz.foodapp.data.entity.restaurant.Restaurant
import com.afurkantitiz.foodapp.databinding.ItemRestaurantsCardBinding
import com.bumptech.glide.Glide

class RestaurantsAdapter : RecyclerView.Adapter<RestaurantsAdapter.AllRestaurantsViewHolder>(){

    private lateinit var restaurantList: List<Restaurant>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllRestaurantsViewHolder {
        val binding = ItemRestaurantsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllRestaurantsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllRestaurantsViewHolder, position: Int) {
        val allRestaurant: Restaurant = restaurantList[position]

        holder.binding.itemRestaurantsName.text = allRestaurant.name
        holder.binding.itemRestaurantsMealCount.text = allRestaurant.minimumDeliveryFee

        Glide
            .with(holder.binding.itemRestaurantsImageView.context)
            .load(allRestaurant.image)
            .into(holder.binding.itemRestaurantsImageView)

        holder.binding.itemRestaurantsCardView.setOnClickListener {
            val action =
                RestaurantsFragmentDirections.actionRestaurantsFragmentToDetailRestaurantFragment(
                    allRestaurant.id
                )
            it.findNavController().navigate(action)
        }
    }

    fun setData(restaurantList: List<Restaurant>?) {
        restaurantList?.let {
            this.restaurantList = restaurantList
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = restaurantList.size

    inner class AllRestaurantsViewHolder(val binding: ItemRestaurantsCardBinding): RecyclerView.ViewHolder(binding.root)
}