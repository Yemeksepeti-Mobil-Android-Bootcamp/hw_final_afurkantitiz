package com.afurkantitiz.foodapp.data.remote

import com.afurkantitiz.foodapp.data.entity.signin.SignInRequest
import com.afurkantitiz.foodapp.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService): BaseDataSource(){
    suspend fun postLogin(request: SignInRequest) = getResult {
        apiService.signIn(request)
    }
}