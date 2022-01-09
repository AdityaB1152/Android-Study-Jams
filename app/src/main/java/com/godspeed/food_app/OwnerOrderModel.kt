package com.godspeed.food_app.data

data class OwnerOrderModel(
    var id: String = "",
    var date: String ="Date",
    var orderNo: Long = 0,
    var price: Long = 0,
    var orderItem: String = "Order_item",
    var name: String ="name",
    var status : String ="") {
}