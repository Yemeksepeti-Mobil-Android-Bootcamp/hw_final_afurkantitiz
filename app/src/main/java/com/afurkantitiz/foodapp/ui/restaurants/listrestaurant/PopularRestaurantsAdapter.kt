package com.afurkantitiz.foodapp.ui.restaurants.listrestaurant

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.data.entity.Restaurant
import com.afurkantitiz.foodapp.databinding.ItemPopularRestaurantCardBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.squareup.picasso.Picasso

class PopularRestaurantsAdapter(private val popularRestaurantsList: ArrayList<Restaurant>, private val mContext: Context)
    :RecyclerView.Adapter<PopularRestaurantsAdapter.PopularRestaurantsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): PopularRestaurantsViewHolder {
        val binding = ItemPopularRestaurantCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularRestaurantsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularRestaurantsViewHolder, position: Int) {
        val popularRestaurant: Restaurant = popularRestaurantsList[position]

        holder.binding.popularRestaurantNameText.text = popularRestaurant.restaurantName

        Glide
            .with(mContext)
            .load(popularRestaurant.restaurantImage)
            .into(holder.binding.popularRestaurantImageView)

        /*Picasso
            .get()
            .load(popularRestaurant.restaurantImage)
            .into(holder.binding.popularRestaurantImageView)*/
    }

    override fun getItemCount(): Int = popularRestaurantsList.size

    inner class PopularRestaurantsViewHolder(val binding: ItemPopularRestaurantCardBinding): RecyclerView.ViewHolder(binding.root)
}