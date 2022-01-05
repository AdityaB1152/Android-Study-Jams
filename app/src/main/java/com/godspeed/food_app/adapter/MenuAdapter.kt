package com.godspeed.food_app.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.food_app.R
import com.google.android.material.imageview.ShapeableImageView
import org.w3c.dom.Text

class MenuAdapter(private var myListener: OnItemClickListener, private val menuList : ArrayList<Menu>) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.menu_items,parent,false)
        return MenuViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {

        val currentMenuItem = menuList[position]
        holder.menuImage.setImageResource(currentMenuItem.menuImage)
        holder.menuName.text = currentMenuItem.menuName
        holder.menuPrice.text= currentMenuItem.menuPrice.toString()


    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val menuImage : ShapeableImageView = itemView.findViewById(R.id.ivMenu)
        val menuPrice : TextView = itemView.findViewById(R.id.tvMenuPrice)
        val menuName : TextView = itemView.findViewById(R.id.tvMenuName)


        init {
            itemView.findViewById<Button>(R.id.btnAddCart).setOnClickListener(this)
        }


        override fun onClick(p0: View?) {
            val position = bindingAdapterPosition
            if (position!= RecyclerView.NO_POSITION) {
                myListener.onItemClick(position)
            }
        }


    }
}