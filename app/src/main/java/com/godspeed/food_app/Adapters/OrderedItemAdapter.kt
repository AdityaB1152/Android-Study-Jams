package com.godspeed.food_app.Adapters
//
//import android.content.Context
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.godspeed.food_app.Model.OrderedItem
//import com.godspeed.food_app.databinding.SampleOrderdItemBinding
//
//class OrderedItemAdapter(private val itemList:List<OrderedItem>, private val context: Context) :
//    RecyclerView.Adapter<OrderedItemAdapter.OrderedViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderedViewHolder {
//
//    }
//
//    override fun onBindViewHolder(holder: OrderedViewHolder, position: Int) {
//        val item:OrderedItem = itemList.get(position)
//    }
//
//    override fun getItemCount(): Int {
//        return itemList.size
//    }
//
//    inner  class OrderedViewHolder(val binding: SampleOrderdItemBinding):
//        RecyclerView.ViewHolder(binding.root)
//}