package com.godspeed.food_app.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao

interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMenuToCart(cart: Cart)

    @Query("SELECT * FROM menuCart")
    fun getAllMenu(): LiveData<List<Cart>>

    @Delete
    suspend fun deleteMenu(cart: Cart)

}