package com.afurkantitiz.foodapp.data

import com.afurkantitiz.foodapp.data.entity.signin.SignInRequest
import com.afurkantitiz.foodapp.data.local.LocalDataSource
import com.afurkantitiz.foodapp.data.remote.RemoteDataSource
import com.afurkantitiz.foodapp.utils.performAuthTokenNetworkOperation
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
}