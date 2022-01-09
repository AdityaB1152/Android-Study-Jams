package com.godspeed.food_app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "menuCart"
)
data class Cart(

    val menuImage : Int,
    val menuName : String,
    val menuPrice : Int,
    val menuQuantity : Int
    ){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

}
