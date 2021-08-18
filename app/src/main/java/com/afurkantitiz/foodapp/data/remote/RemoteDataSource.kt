package com.afurkantitiz.foodapp.data.remote

import com.afurkantitiz.foodapp.data.entity.signin.SignInRequest
import com.afurkantitiz.foodapp.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService): BaseDataSource(){
    suspend fun postLogin(request: SignInRequest) = getResult {
        apiService.signIn(request)
    }

    suspend fun getRestaurants() = getResult { apiService.getRestaurants() }

    suspend fun getRestaurantsByCuisine(cuisine: String) =
        getResult { apiService.getRestaurantsByCuisine(cuisine) }

    suspend fun getRestaurantById(id: String) = getResult { apiService.getRestaurantById(id) }

    suspend fun getMealById(id: String) = getResult { apiService.getMealById(id) }
}