package com.godspeed.food_app.data

import java.util.*
import kotlin.collections.HashMap

data class OrderClass(val name:String ,val price:Int , val items:HashMap<String , Cart>, val orderNum:Int ,
                      val status:String , val date: Date) {

}