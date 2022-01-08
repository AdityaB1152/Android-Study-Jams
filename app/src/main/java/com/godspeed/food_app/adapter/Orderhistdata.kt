package com.godspeed.food_app.data

data class OrderHistoryItem(
    var id: String = "",
    var date: String = "Date",
    var orderStatus: String = "Order_status",
    var Total: Long = 0,
    var ordid: Long = 0,
    var items: String = "Items")