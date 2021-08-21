package com.afurkantitiz.foodapp.data.entity.cart

import androidx.room.*
import com.afurkantitiz.foodapp.data.entity.food.Meal

@Entity(tableName = "cartInfo")
data class Cart(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id : Int = 0,
    @ColumnInfo(name = "food") val mealName: String,
    @ColumnInfo(name = "restaurantName") val restaurantName: String,
    @ColumnInfo(name = "foodImage") val foodImage: String,
    @ColumnInfo(name = "foodPrice") val foodPrice: String,
    @ColumnInfo(name = "restaurantId") val restaurantId: String,
    @ColumnInfo(name = "foodId") val foodId: String)