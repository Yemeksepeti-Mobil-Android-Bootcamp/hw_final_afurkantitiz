package com.afurkantitiz.foodapp.ui.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.afurkantitiz.foodapp.data.ApiRepository
import com.afurkantitiz.foodapp.data.entity.Cart
import com.afurkantitiz.foodapp.data.local.RoomDb
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel@Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository,
    var app: Application
) : ViewModel() {
    var allCarts: ArrayList<Cart> = ArrayList()

    fun getAllCart(){
        val cartDao = RoomDb.getAppDatabase(app)?.cartDao()
        allCarts = cartDao?.getCarts() as ArrayList<Cart>
    }

    fun insertCart(cart: Cart){
        val cartDao = RoomDb.getAppDatabase(app)?.cartDao()
        cartDao?.insertCart(cart)
        getAllCart()
    }
}