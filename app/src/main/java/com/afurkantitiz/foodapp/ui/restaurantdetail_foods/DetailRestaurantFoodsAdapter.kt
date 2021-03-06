package com.afurkantitiz.foodapp.ui.restaurantdetail_foods

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.afurkantitiz.foodapp.data.entity.food.Meal
import com.afurkantitiz.foodapp.databinding.ItemMealCardBinding
import com.bumptech.glide.Glide

class DetailRestaurantFoodsAdapter :
    RecyclerView.Adapter<DetailRestaurantFoodsAdapter.AllMealsViewHolder>() {
    private lateinit var mealList: List<Meal>
    private lateinit var restaurantId: String
    private lateinit var restaurantName: String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllMealsViewHolder {
        val binding =
            ItemMealCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllMealsViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AllMealsViewHolder, position: Int) {
        val allMeal: Meal = mealList[position]

        holder.binding.itemMealsNameText.text = allMeal.name
        holder.binding.itemMealsPrice.text = allMeal.price + " $"

        Glide
            .with(holder.binding.itemMealsImageView.context)
            .load(allMeal.image)
            .into(holder.binding.itemMealsImageView)

        holder.binding.itemMealsCardView.setOnClickListener {
            val action =
                DetailRestaurantFoodsFragmentDirections.actionDetailRestaurantFragmentToDetailMealFragment(
                    allMeal.id,
                    restaurantId,
                    restaurantName
                )
            it.findNavController().navigate(action)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMealList(mealList: List<Meal>, restaurantId: String, restaurantName: String) {
        this.restaurantId = restaurantId
        this.mealList = mealList
        this.restaurantName = restaurantName
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mealList.size

    inner class AllMealsViewHolder(val binding: ItemMealCardBinding) :
        RecyclerView.ViewHolder(binding.root)
}