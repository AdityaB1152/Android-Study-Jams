package com.godspeed.food_app.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.godspeed.food_app.data.Cart
import com.godspeed.food_app.data.CartDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CartRepository {
    companion object{
        private var cartDatabase: CartDatabase?=null
        private fun initialiseDB(context: Context): CartDatabase? {
            return CartDatabase.getInstance(context)
        }
        fun addMenuToCart(context: Context,cart: Cart){
            cartDatabase = initialiseDB(context)
            CoroutineScope(IO).launch {
                cartDatabase?.getCartDao()?.addMenuToCart(cart)
            }
        }

        fun getAllMenu(context: Context): LiveData<List<Cart>>? {
            cartDatabase= initialiseDB(context)
            return cartDatabase?.getCartDao()?.getAllMenu()
        }
        fun deleteMenu(context: Context,cart: Cart) {
            cartDatabase = initialiseDB(context)
            CoroutineScope(IO).launch {
                cartDatabase?.getCartDao()?.deleteMenu(cart)
            }
        }



    }
}