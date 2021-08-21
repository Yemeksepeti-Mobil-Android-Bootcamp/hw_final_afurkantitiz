package com.afurkantitiz.foodapp.data.entity.order

import com.afurkantitiz.foodapp.data.entity.food.Meal
import com.google.gson.annotations.SerializedName
import java.util.*

data class Order(
    @SerializedName("createdDate")
    val createdDate: Date,
    @SerializedName("_id")
    val id: String,
    @SerializedName("meals")
    val meals: List<Meal>,
    @SerializedName("restaurant")
    val restaurant: OrderRestaurant,
    @SerializedName("user")
    val user: String)