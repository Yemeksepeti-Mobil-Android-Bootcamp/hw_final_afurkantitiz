package com.afurkantitiz.foodapp.data.entity.cart

import com.google.gson.annotations.SerializedName

data class CartRequest(
    @SerializedName("restaurantId")
    val restaurantId: String,
    @SerializedName("mealIds")
    val mealIds: ArrayList<String>
)