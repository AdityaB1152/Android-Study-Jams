package com.godspeed.food_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Cart::class],
    version = 1
)

abstract class CartDatabase : RoomDatabase(){
    abstract fun getCartDao() : CartDao

    companion object{
        private const val DATABASE_NAME = "CartDatabase"

        @Volatile
        var instance:CartDatabase?=null

        fun getInstance(context: Context): CartDatabase?{

            if (instance == null){
                synchronized(CartDatabase::class.java){
                    if (instance==null){
                        instance= Room.databaseBuilder(context,
                            CartDatabase::class.java,
                            DATABASE_NAME)
                            .build()
                    }
                }
            }
            return instance
        }
    }
}