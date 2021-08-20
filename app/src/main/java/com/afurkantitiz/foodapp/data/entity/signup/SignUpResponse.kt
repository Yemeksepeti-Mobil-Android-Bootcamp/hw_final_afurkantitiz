package com.afurkantitiz.foodapp.data.entity.signup

import com.google.gson.annotations.SerializedName

class SignUpResponse(
    @SerializedName("data")
    val registerData: SignUpData,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("token")
    val token: String
)