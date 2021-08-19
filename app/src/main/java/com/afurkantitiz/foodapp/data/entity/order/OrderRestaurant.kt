package com.afurkantitiz.foodapp.data.entity.order

import com.google.gson.annotations.SerializedName

data class OrderRestaurant(
    @SerializedName("name")
    val name: String)