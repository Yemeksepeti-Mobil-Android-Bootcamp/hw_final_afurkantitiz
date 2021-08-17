package com.afurkantitiz.foodapp.data.entity.signin

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("data")
    val loginData: SignInData,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("token")
    val token: String
)