package com.afurkantitiz.foodapp.ui.restaurantdetail_foods

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afurkantitiz.foodapp.data.entity.food.Ingredient
import com.afurkantitiz.foodapp.databinding.ItemAddIngredientCardBinding

class IngredientsAdapter(private val ingredientList: MutableList<Ingredient>) :
    RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder>() {
    private var ingredientClickListener: IIngredientOnClick? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientViewHolder {
        val binding =
            ItemAddIngredientCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientsAdapter.IngredientViewHolder, position: Int) {
        val ingredient = ingredientList[position]

        holder.binding.addIngredientChip.text = ingredient.ingredient
    }

    override fun getItemCount(): Int = ingredientList.size

    fun addListener(listener: IIngredientOnClick) {
        ingredientClickListener = listener
    }

    inner class IngredientViewHolder(val binding: ItemAddIngredientCardBinding) :
        RecyclerView.ViewHolder(binding.root)
}