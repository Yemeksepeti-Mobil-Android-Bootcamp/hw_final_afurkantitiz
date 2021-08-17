package com.afurkantitiz.foodapp.data.remote

import com.afurkantitiz.foodapp.data.entity.signin.SignInRequest
import com.afurkantitiz.foodapp.data.entity.signin.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    @POST("auth/login")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>
}