package com.afurkantitiz.foodapp.ui.restaurants.detailmeal

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afurkantitiz.foodapp.databinding.ItemIngredientCardBinding

class DetailMealAdapter : RecyclerView.Adapter<DetailMealAdapter.DetailMealViewHolder>() {
    private lateinit var ingredients: List<String>

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): DetailMealAdapter.DetailMealViewHolder {
        val binding = ItemIngredientCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailMealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailMealAdapter.DetailMealViewHolder, position: Int) {
        val ingredient = ingredients[position]

        holder.binding.itemChip.text = ingredient

        holder.binding.itemChip.setOnClickListener {
            if (holder.binding.itemChip.paintFlags == Paint.STRIKE_THRU_TEXT_FLAG){
                holder.binding.itemChip.paintFlags = 0
            }else {
                holder.binding.itemChip.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
        }
    }

    fun setIngredientList(ingredientList: List<String>) {
        this.ingredients = ingredientList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = ingredients.size

    inner class DetailMealViewHolder(val binding: ItemIngredientCardBinding): RecyclerView.ViewHolder(binding.root)
}