package com.godspeed.food_app.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.godspeed.food_app.data.Cart
import com.godspeed.food_app.repository.CartRepository

class CartViewModel: ViewModel() {

    fun addMenuToCart(context: Context,cart: Cart){
        CartRepository.addMenuToCart(context,cart)

    }
    fun getAllMenu(context: Context): LiveData<List<Cart>>?{
        return CartRepository.getAllMenu(context)

    }
    fun deleteMenu(context: Context,cart: Cart){
        return CartRepository.deleteMenu(context, cart)
    }

}