package com.afurkantitiz.foodapp.data.entity.signup

import com.google.gson.annotations.SerializedName

data class SignUpData(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("role")
    val role: String
)