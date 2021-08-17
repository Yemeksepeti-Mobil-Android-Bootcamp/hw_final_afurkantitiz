package com.afurkantitiz.foodapp.ui.restaurants.detailrestaurant

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afurkantitiz.foodapp.data.entity.Meal
import com.afurkantitiz.foodapp.databinding.ItemMealCardBinding
import com.bumptech.glide.Glide

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

        Glide
            .with(mContext)
            .load(allMeal.mealImage)
            .into(holder.binding.itemMealsImageView)

        /*holder.binding.itemMealsCardView.setOnClickListener {

        }*/
    }

    override fun getItemCount(): Int = allMealsList.size

    inner class AllMealsViewHolder(val binding: ItemMealCardBinding): RecyclerView.ViewHolder(binding.root)
}