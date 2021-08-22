package com.afurkantitiz.foodapp.data.remote

import com.afurkantitiz.foodapp.data.entity.cart.CartRequest
import com.afurkantitiz.foodapp.data.entity.mealadd.MealAddRequest
import com.afurkantitiz.foodapp.data.entity.profile.UserRequest
import com.afurkantitiz.foodapp.data.entity.restaurantadd.RestaurantAddRequest
import com.afurkantitiz.foodapp.data.entity.signin.SignInRequest
import com.afurkantitiz.foodapp.data.entity.signup.SignUpRequest
import com.afurkantitiz.foodapp.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService): BaseDataSource(){
    suspend fun postLogin(request: SignInRequest) = getResult {
        apiService.signIn(request)
    }

    suspend fun postRegister(request: SignUpRequest) = getResult {
        apiService.register(request)
    }

    suspend fun getRestaurants() = getResult { apiService.getRestaurants() }

    suspend fun postRestaurant(request: RestaurantAddRequest) = getResult { apiService.postRestaurant(request) }

    suspend fun getRestaurantsByCuisine(cuisine: String) =
        getResult { apiService.getRestaurantsByCuisine(cuisine) }

    suspend fun getRestaurantById(id: String) = getResult { apiService.getRestaurantById(id) }

    suspend fun getMealById(id: String) = getResult { apiService.getMealById(id) }

    suspend fun getUser() = getResult { apiService.getUser() }

    suspend fun updateUser(request : UserRequest) = getResult { apiService.updateUser(request) }

    suspend fun getOrders() = getResult { apiService.getOrders() }

    suspend fun postOrders(request: CartRequest) = getResult {
        apiService.postOrders(request)
    }

    suspend fun postMeal(restaurantId: String, request: MealAddRequest) = getResult {
        apiService.postMeal(restaurantId, request)
    }
}