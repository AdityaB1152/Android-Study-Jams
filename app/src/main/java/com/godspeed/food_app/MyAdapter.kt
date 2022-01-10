package com.godspeed.food_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.godspeed.food_app.MyAdapter.MyViewHolder
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter (private val newList:ArrayList<News>): RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=newList[position]
        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.titleOfFood.text=currentItem.heading
        holder.foodPrice.text=currentItem.cost
    }

    override fun getItemCount() = newList.size

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleImage : ShapeableImageView =itemView.findViewById(R.id.title_image)
        val titleOfFood: TextView =itemView.findViewById(R.id.name_of_food)
        val foodPrice: TextView =itemView.findViewById(R.id.cost_of_item)
    }

}
