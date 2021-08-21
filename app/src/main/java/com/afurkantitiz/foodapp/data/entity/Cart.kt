package com.afurkantitiz.foodapp.data.entity

import androidx.room.*
import com.afurkantitiz.foodapp.data.entity.food.Meal

@Entity(tableName = "cartInfo")
data class Cart(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id : Int = 0,
    @TypeConverters
    @ColumnInfo(name = "food") val mealName: String,
    @ColumnInfo(name = "restaurantName") val restaurantName: String){}