package com.godspeed.food_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.food_app.R
import com.google.android.material.imageview.ShapeableImageView
import org.w3c.dom.Text

class MenuAdapter(private var context: Context,private var myListener: OnItemClickListener, private val menuList : ArrayList<Menu>) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {



    interface OnItemClickListener{
        fun onItemClick(position: Int,qty : Int)
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


        holder.incBtn.setOnClickListener {
            currentMenuItem.menuQuantity++
            holder.menuQty.text = currentMenuItem.menuQuantity.toString()
        }
        holder.decBtn.setOnClickListener {
            if (currentMenuItem.menuQuantity>1){
                currentMenuItem.menuQuantity --
                holder.menuQty.text = currentMenuItem.menuQuantity.toString()
            }
            else{
                Toast.makeText(context,"Qty cant be less than 1",Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val menuImage : ShapeableImageView = itemView.findViewById(R.id.ivMenu)
        val menuPrice : TextView = itemView.findViewById(R.id.tvMenuPrice)
        val menuName : TextView = itemView.findViewById(R.id.tvMenuName)
        val incBtn : Button = itemView.findViewById(R.id.btnInc)
        val decBtn : Button = itemView.findViewById(R.id.btnDec)
        var menuQty : TextView = itemView.findViewById(R.id.tvQty)



        init {
            itemView.findViewById<Button>(R.id.btnAddCart).setOnClickListener(this)
        }


        override fun onClick(p0: View?) {
            val position = bindingAdapterPosition
            val qty = menuList[position].menuQuantity
            if (position!= RecyclerView.NO_POSITION) {
                myListener.onItemClick(position,qty)
            }
        }


    }
}