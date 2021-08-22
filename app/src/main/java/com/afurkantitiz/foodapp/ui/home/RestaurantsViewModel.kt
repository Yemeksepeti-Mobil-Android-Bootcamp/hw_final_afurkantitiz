package com.afurkantitiz.foodapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.data.ApiRepository
import com.afurkantitiz.foodapp.data.entity.Categories
import com.afurkantitiz.foodapp.data.entity.profile.UserResponse
import com.afurkantitiz.foodapp.data.entity.restaurant.Restaurant
import com.afurkantitiz.foodapp.data.entity.restaurant.RestaurantListResponse
import com.afurkantitiz.foodapp.data.entity.restaurantadd.RestaurantAddRequest
import com.afurkantitiz.foodapp.data.entity.restaurantadd.RestaurantAddResponse
import com.afurkantitiz.foodapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {
    var restaurantList: List<Restaurant>? = null

    fun getRestaurants(): LiveData<Resource<RestaurantListResponse>> =
        apiRepository.getRestaurants()

    fun getRestaurantByCuisine(cuisine: String): LiveData<Resource<RestaurantListResponse>> =
        apiRepository.getRestaurantByCuisine(cuisine)

    fun getCategories(): ArrayList<Categories> {
        return arrayListOf(
            Categories("All", R.drawable.ic_dish),
            Categories("Burger", R.drawable.ic_burger),
            Categories("Cake", R.drawable.ic_cake),
            Categories("Chicken", R.drawable.ic_chicken),
            Categories("Pizza", R.drawable.ic_pizza_slice),
            Categories("Skewer", R.drawable.ic_skewer),
            Categories("Soup", R.drawable.ic_soup)
        )
    }

    fun searchViewForRestaurants(query: String?): List<Restaurant>? {
        if (query.isNullOrEmpty())
            return restaurantList

        val filterList: MutableList<Restaurant> = mutableListOf()
        restaurantList?.forEach { restaurant ->
            if (restaurant.name.contains(query, true))
                filterList.add(restaurant)
            else if (restaurant.district.contains(query, true))
                filterList.add(restaurant)
        }
        return filterList
    }

    fun addRestaurant(
        name: String,
        cuisine: String,
        deliveryInfo: String,
        deliveryTime: String,
        address: String,
        district: String,
        minDeliveryFee: String,
        paymentMethods: String,
        rating: Double,
        imageUrl: String
    ): LiveData<Resource<RestaurantAddResponse>> {

        val request = RestaurantAddRequest(
            name,
            cuisine,
            deliveryInfo,
            deliveryTime,
            address,
            district,
            minDeliveryFee,
            paymentMethods,
            rating,
            imageUrl
        )

        return apiRepository.postRestaurant(request)
    }

    fun getUser(): LiveData<Resource<UserResponse>> = apiRepository.getUser()
}