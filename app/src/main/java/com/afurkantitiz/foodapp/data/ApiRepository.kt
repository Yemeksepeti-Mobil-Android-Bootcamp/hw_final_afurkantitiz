package com.afurkantitiz.foodapp.data

import com.afurkantitiz.foodapp.data.entity.signin.SignInRequest
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

    fun getRestaurants() =
        performNetworkOperation {
            remoteDataSource.getRestaurants()
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

    fun getOrder() = performNetworkOperation {
            remoteDataSource.getOrders()
        }
}