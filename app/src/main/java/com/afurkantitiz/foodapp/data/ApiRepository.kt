package com.afurkantitiz.foodapp.data

import com.afurkantitiz.foodapp.data.entity.cart.CartRequest
import com.afurkantitiz.foodapp.data.entity.mealadd.MealAddRequest
import com.afurkantitiz.foodapp.data.entity.profile.UserRequest
import com.afurkantitiz.foodapp.data.entity.restaurantadd.RestaurantAddRequest
import com.afurkantitiz.foodapp.data.entity.signin.SignInRequest
import com.afurkantitiz.foodapp.data.entity.signup.SignUpRequest
import com.afurkantitiz.foodapp.data.local.LocalDataSource
import com.afurkantitiz.foodapp.data.remote.RemoteDataSource
import com.afurkantitiz.foodapp.utils.performAuthTokenNetworkOperation
import com.afurkantitiz.foodapp.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var localDataSource: LocalDataSource,
    private var remoteDataSource: RemoteDataSource
) {
    fun login(request: SignInRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.postLogin(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )

    fun signUp(request: SignUpRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.postRegister(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )

    fun postOrders(request: CartRequest) =
        performNetworkOperation {
            remoteDataSource.postOrders(request)
        }

    fun getRestaurants() =
        performNetworkOperation {
            remoteDataSource.getRestaurants()
        }

    fun postRestaurant(restaurantAddRequest: RestaurantAddRequest) =
        performNetworkOperation {
            remoteDataSource.postRestaurant(request = restaurantAddRequest)
        }

    fun getRestaurantById(id: String) =
        performNetworkOperation {
            remoteDataSource.getRestaurantById(id)
        }

    fun getMealById(id: String) =
        performNetworkOperation {
            remoteDataSource.getMealById(id)
        }

    fun getRestaurantByCuisine(cuisine: String) =
        performNetworkOperation {
            remoteDataSource.getRestaurantsByCuisine(cuisine)
        }

    fun getUser() = performNetworkOperation {
        remoteDataSource.getUser()
    }

    fun updateUser(user : UserRequest) = performNetworkOperation {
        remoteDataSource.updateUser(request = user)
    }

    fun getOrder() = performNetworkOperation {
            remoteDataSource.getOrders()
        }

    fun postMeal(restaurantId: String, mealAddRequest: MealAddRequest) =
        performNetworkOperation {
            remoteDataSource.postMeal(restaurantId, request = mealAddRequest)
        }
}