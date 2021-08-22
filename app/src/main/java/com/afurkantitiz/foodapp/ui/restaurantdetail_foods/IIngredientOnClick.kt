package com.afurkantitiz.foodapp.ui.restaurantdetail_foods

import com.afurkantitiz.foodapp.data.entity.food.Ingredient

interface IIngredientOnClick {
    fun onIngredientClickListener(ingredient: Ingredient, position: Int)
}