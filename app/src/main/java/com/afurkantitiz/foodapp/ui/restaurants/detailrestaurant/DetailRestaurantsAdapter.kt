package com.afurkantitiz.foodapp.ui.restaurants.detailrestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.data.entity.meal.Meal
import com.afurkantitiz.foodapp.databinding.ItemMealCardBinding
import com.bumptech.glide.Glide

class DetailRestaurantsAdapter : RecyclerView.Adapter<DetailRestaurantsAdapter.AllMealsViewHolder>(){
    private lateinit var mealList: List<Meal>
    private lateinit var restaurantId: String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllMealsViewHolder {
        val binding = ItemMealCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllMealsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllMealsViewHolder, position: Int) {
        val allMeal: Meal = mealList[position]

        holder.binding.itemMealsNameText.text = allMeal.name
        holder.binding.itemMealsPrice.text = allMeal.price

        Glide
            .with(holder.binding.itemMealsImageView.context)
            .load(allMeal.image)
            .into(holder.binding.itemMealsImageView)

        holder.binding.itemMealsCardView.setOnClickListener {
            val action =
                DetailRestaurantFragmentDirections.actionDetailRestaurantFragmentToDetailMealFragment(allMeal.id,restaurantId)
            it.findNavController().navigate(action)
        }
    }

    fun setMealList(mealList: List<Meal>, restaurantId: String) {
        this.restaurantId = restaurantId
        this.mealList = mealList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mealList.size

    inner class AllMealsViewHolder(val binding: ItemMealCardBinding): RecyclerView.ViewHolder(binding.root)
}