package com.afurkantitiz.foodapp.ui.restaurantdetail_foods

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.afurkantitiz.foodapp.data.ApiRepository
import com.afurkantitiz.foodapp.data.entity.mealadd.MealAddRequest
import com.afurkantitiz.foodapp.data.entity.mealadd.MealAddResponse
import com.afurkantitiz.foodapp.data.entity.profile.UserResponse
import com.afurkantitiz.foodapp.data.entity.restaurant.RestaurantResponse
import com.afurkantitiz.foodapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailRestaurantFoodsViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {
    fun getRestaurantDetail(id: String): LiveData<Resource<RestaurantResponse>> =
        apiRepository.getRestaurantById(id)

    fun addMeal(
        restaurantId: String,
        name: String,
        imageUrl: String,
        price: Double,
        ingredients: List<String>,
        description: String
    ): LiveData<Resource<MealAddResponse>> {
        val request = MealAddRequest(name, imageUrl, description, price, ingredients)
        return apiRepository.postMeal(restaurantId, request)
    }

    fun getUser(): LiveData<Resource<UserResponse>> = apiRepository.getUser()
}