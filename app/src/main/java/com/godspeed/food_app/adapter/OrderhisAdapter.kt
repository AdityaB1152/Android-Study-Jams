package com.godspeed.food_app

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.godspeed.food_app.data.OrderHistoryItem
import com.godspeed.food_app.fragment.OrderHistoryFragment


class OrderHistoryAdapter(private var orderHistoryList: ArrayList<OrderHistoryItem>) : RecyclerView.Adapter<OrderHistoryAdapter.ItemViewHolder>() {

    class ItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val date = v.findViewById<TextView>(R.id.order_date)
        val orderID = v.findViewById<TextView>(R.id.ord_number)
        val orderStatus = v.findViewById<TextView>(R.id.ord_his_status)
        val price = v.findViewById<TextView>(R.id.pricetot)
        val item = v.findViewById<TextView>(R.id.items)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adaptlayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.orderhistoryitems, parent, false)

        return ItemViewHolder(adaptlayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val orderitems=orderHistoryList[position]

        holder.date.text=orderitems.date
        holder.orderStatus.text=orderitems.orderStatus
        holder.price.text=orderitems.Total.toString()
        holder.orderID.text=orderitems.ordid.toString()
        holder.item.text=orderitems.items.toString()
    }
    override fun getItemCount() = orderHistoryList.size
}


