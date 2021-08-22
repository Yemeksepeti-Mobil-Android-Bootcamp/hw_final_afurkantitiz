package com.afurkantitiz.foodapp.ui.cart

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.afurkantitiz.foodapp.data.ApiRepository
import com.afurkantitiz.foodapp.data.entity.cart.Cart
import com.afurkantitiz.foodapp.data.entity.cart.CartRequest
import com.afurkantitiz.foodapp.data.entity.cart.CartResponse
import com.afurkantitiz.foodapp.data.local.RoomDb
import com.afurkantitiz.foodapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository,
    var app: Application
) : ViewModel() {
    var allCarts: ArrayList<Cart> = ArrayList()

    fun getAllCart() {
        val cartDao = RoomDb.getAppDatabase(app)?.cartDao()
        allCarts = cartDao?.getCarts() as ArrayList<Cart>
    }

    fun insertCart(cart: Cart) {
        val cartDao = RoomDb.getAppDatabase(app)?.cartDao()
        cartDao?.insertCart(cart)
        getAllCart()
    }

    fun addOrderBulk(
        restaurantId: String,
        foodId: ArrayList<String>
    ): LiveData<Resource<CartResponse>> {
        val request = CartRequest(restaurantId, foodId)
        return apiRepository.postOrders(request)
    }

    fun deleteCart(cartList: ArrayList<Cart>) {
        val cartDao = RoomDb.getAppDatabase(app)?.cartDao()
        cartDao?.deleteCart(cartList)
        getAllCart()
    }
}