package com.afurkantitiz.foodapp.ui.fooddetail

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afurkantitiz.foodapp.databinding.ItemIngredientCardBinding

class DetailFoodAdapter : RecyclerView.Adapter<DetailFoodAdapter.DetailMealViewHolder>() {
    private lateinit var ingredients: List<String>

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): DetailMealViewHolder {
        val binding = ItemIngredientCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailMealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailMealViewHolder, position: Int) {
        val ingredient = ingredients[position]

        holder.binding.textChip.text = ingredient

        holder.binding.itemChip.setOnClickListener {
            if (holder.binding.textChip.paintFlags == Paint.STRIKE_THRU_TEXT_FLAG){
                holder.binding.textChip.paintFlags = 0
            }else {
                holder.binding.textChip.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
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