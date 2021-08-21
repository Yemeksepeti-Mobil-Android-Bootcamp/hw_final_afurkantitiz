package com.afurkantitiz.foodapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.afurkantitiz.foodapp.data.entity.cart.Cart

@Dao
interface CartDao {
    @Query("SELECT * FROM cartInfo ORDER BY id DESC")
    fun getCarts(): List<Cart>

    @Insert
    fun insertCart(cart: Cart?)

    @Delete
    fun deleteCart(cart: Cart?)
}