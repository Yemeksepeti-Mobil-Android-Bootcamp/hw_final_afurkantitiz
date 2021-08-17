package com.afurkantitiz.foodapp.ui.restaurants.detailrestaurant

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.data.entity.Meal
import com.afurkantitiz.foodapp.databinding.ItemMealCardBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

import com.bumptech.glide.load.resource.bitmap.CenterCrop

import com.bumptech.glide.request.RequestOptions




class DetailRestaurantsAdapter(private val allMealsList: ArrayList<Meal>, private val mContext: Context)
    : RecyclerView.Adapter<DetailRestaurantsAdapter.AllMealsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllMealsViewHolder {
        val binding = ItemMealCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllMealsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllMealsViewHolder, position: Int) {
        val allMeal: Meal = allMealsList[position]

        holder.binding.itemMealsNameText.text = allMeal.mealName
        holder.binding.itemMealsPrice.text = allMeal.mealPrice

        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(RoundedCorners(16))

        Glide
            .with(mContext)
            .load(allMeal.mealImage)
            .apply(requestOptions)
            .into(holder.binding.itemMealsImageView)

        holder.binding.itemMealsCardView.setOnClickListener {
            it.findNavController().navigate(R.id.action_detailRestaurantFragment_to_detailMealFragment)
        }
    }

    override fun getItemCount(): Int = allMealsList.size

    inner class AllMealsViewHolder(val binding: ItemMealCardBinding): RecyclerView.ViewHolder(binding.root)
}