package com.afurkantitiz.foodapp.data.entity.order

import com.google.gson.annotations.SerializedName

data class OrderListResponse(
    @SerializedName("data")
    val orderList: List<Order>,
    @SerializedName("success")
    val success: Boolean
)