package com.afurkantitiz.foodapp.data.remote

import com.afurkantitiz.foodapp.data.entity.meal.MealResponse
import com.afurkantitiz.foodapp.data.entity.profile.UserResponse
import com.afurkantitiz.foodapp.data.entity.restaurant.RestaurantListResponse
import com.afurkantitiz.foodapp.data.entity.restaurant.RestaurantResponse
import com.afurkantitiz.foodapp.data.entity.signin.SignInRequest
import com.afurkantitiz.foodapp.data.entity.signin.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIService {
    @POST("auth/login")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>

    @GET("a/restaurant")
    suspend fun getRestaurants(): Response<RestaurantListResponse>

    @GET("a/restaurant/cuisine/{cuisineName}")
    suspend fun getRestaurantsByCuisine(@Path("cuisineName") cuisine: String): Response<RestaurantListResponse>

    @GET("a/restaurant/{id}")
    suspend fun getRestaurantById(@Path("id") id: String): Response<RestaurantResponse>

    @GET("a/meal/{id}")
    suspend fun getMealById(@Path("id") id: String): Response<MealResponse>

    @GET("auth/profile")
    suspend fun getUser() : Response<UserResponse>
}