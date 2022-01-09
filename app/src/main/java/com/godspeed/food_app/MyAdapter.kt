package com.godspeed.food_app

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.godspeed.food_app.MyAdapter.MyViewHolder
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter (private val newslist:ArrayList<News>): RecyclerView.Adapter<MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val CurrenItem=newslist[position]
        holder.titleImage.setImageResource(CurrenItem.titleImage)
        holder.title_of_food.text=CurrenItem.heading
        holder.foodcost.text=CurrenItem.cost
    }

    override fun getItemCount(): Int{
        return newslist.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleImage : ShapeableImageView =itemView.findViewById(R.id.title_image)
        val title_of_food: TextView =itemView.findViewById(R.id.name_of_food)
        val foodcost: TextView =itemView.findViewById(R.id.cost_of_item)


    }

}
