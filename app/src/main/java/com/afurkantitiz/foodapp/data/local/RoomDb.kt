package com.afurkantitiz.foodapp.data.local

import android.content.Context
import androidx.room.*
import com.afurkantitiz.foodapp.data.entity.cart.Cart

@Database(entities = [Cart::class], version = 1)
abstract class RoomDb : RoomDatabase(){

   abstract fun cartDao(): CartDao?

    companion object{
        private var INSTANCE: RoomDb?= null

        @TypeConverters
        fun getAppDatabase(context: Context): RoomDb? {
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder<RoomDb>(
                    context.applicationContext, RoomDb::class.java, "AppDB")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}