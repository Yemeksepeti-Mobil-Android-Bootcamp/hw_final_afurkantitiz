package com.afurkantitiz.foodapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.afurkantitiz.foodapp.data.ApiRepository
import com.afurkantitiz.foodapp.data.entity.order.OrderListResponse
import com.afurkantitiz.foodapp.data.entity.profile.User
import com.afurkantitiz.foodapp.data.entity.profile.UserRequest
import com.afurkantitiz.foodapp.data.entity.profile.UserResponse
import com.afurkantitiz.foodapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    fun getUser(): LiveData<Resource<UserResponse>> = apiRepository.getUser()

    fun updateUser(userRequest: UserRequest): LiveData<Resource<User>> =
        apiRepository.updateUser(userRequest)

    fun getOrders(): LiveData<Resource<OrderListResponse>> = apiRepository.getOrder()

    fun logOut() {
        apiRepository.logOut()
    }
}