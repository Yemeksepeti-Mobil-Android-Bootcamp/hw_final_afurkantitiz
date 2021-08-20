package com.afurkantitiz.foodapp.ui.profile.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.afurkantitiz.foodapp.data.ApiRepository
import com.afurkantitiz.foodapp.data.entity.order.OrderResponse
import com.afurkantitiz.foodapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val apiRepository: ApiRepository
) : ViewModel() {

    fun getOrders(): LiveData<Resource<OrderResponse>> =
        apiRepository.getOrder()
}