package com.afurkantitiz.foodapp.data.entity.signin

import com.google.gson.annotations.SerializedName

data class SignInData(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("role")
    val role: String)