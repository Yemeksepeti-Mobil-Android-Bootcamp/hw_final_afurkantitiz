package com.afurkantitiz.foodapp.data.remote

import com.afurkantitiz.foodapp.data.entity.cart.CartRequest
import com.afurkantitiz.foodapp.data.entity.cart.CartResponse
import com.afurkantitiz.foodapp.data.entity.food.MealResponse
import com.afurkantitiz.foodapp.data.entity.mealadd.MealAddRequest
import com.afurkantitiz.foodapp.data.entity.mealadd.MealAddResponse
import com.afurkantitiz.foodapp.data.entity.order.OrderListResponse
import com.afurkantitiz.foodapp.data.entity.profile.User
import com.afurkantitiz.foodapp.data.entity.profile.UserRequest
import com.afurkantitiz.foodapp.data.entity.profile.UserResponse
import com.afurkantitiz.foodapp.data.entity.restaurant.RestaurantListResponse
import com.afurkantitiz.foodapp.data.entity.restaurant.RestaurantResponse
import com.afurkantitiz.foodapp.data.entity.restaurantadd.RestaurantAddRequest
import com.afurkantitiz.foodapp.data.entity.restaurantadd.RestaurantAddResponse
import com.afurkantitiz.foodapp.data.entity.signin.SignInRequest
import com.afurkantitiz.foodapp.data.entity.signin.SignInResponse
import com.afurkantitiz.foodapp.data.entity.signup.SignUpRequest
import com.afurkantitiz.foodapp.data.entity.signup.SignUpResponse
import retrofit2.Response
import retrofit2.http.*

interface APIService {
    @POST("auth/login")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>

    @GET("a/restaurant")
    suspend fun getRestaurants(): Response<RestaurantListResponse>

    @POST("a/restaurant")
    suspend fun postRestaurant(@Body request: RestaurantAddRequest): Response<RestaurantAddResponse>

    @GET("a/restaurant/cuisine/{cuisineName}")
    suspend fun getRestaurantsByCuisine(@Path("cuisineName") cuisine: String): Response<RestaurantListResponse>

    @GET("a/restaurant/{id}")
    suspend fun getRestaurantById(@Path("id") id: String): Response<RestaurantResponse>

    @GET("a/meal/{id}")
    suspend fun getMealById(@Path("id") id: String): Response<MealResponse>

    @GET("auth/profile")
    suspend fun getUser() : Response<UserResponse>

    @PUT("auth/updateDetails")
    suspend fun updateUser(@Body request : UserRequest) : Response<User>

    @GET("a/order/bulk")
    suspend fun getOrders(): Response<OrderListResponse>

    @POST("auth/register")
    suspend fun register(@Body request: SignUpRequest): Response<SignUpResponse>

    @POST("a/order/bulk")
    suspend fun postOrders(@Body request: CartRequest): Response<CartResponse>

    @POST("a/restaurant/{restaurantId}/meal")
    suspend fun postMeal(
        @Path("restaurantId") restaurantId: String,
        @Body request: MealAddRequest
    ): Response<MealAddResponse>
}