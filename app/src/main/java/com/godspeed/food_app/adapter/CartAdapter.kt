package com.godspeed.food_app.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.food_app.R
import com.godspeed.food_app.data.Cart
import com.google.android.material.imageview.ShapeableImageView

class CartAdapter(private val context: Context, private var cartList : ArrayList<Cart>, private var myListener: CartAdapter.OnItemClickListener) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(cart: Cart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cart_items,parent,false)
        return CartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cart:Cart = cartList[position]
        holder.cartMenuImage.setImageResource(cart.menuImage)
        holder.cartMenuName.text = cart.menuName
        holder.cartMenuPrice.text= cart.menuPrice.toString()

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(cartList: ArrayList<Cart>){
        for ( i in cartList){
            Log.d("TAG",i.menuName)
        }
        this.cartList=cartList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
        val cartMenuImage : ShapeableImageView = itemView.findViewById(R.id.ivCartMenu)
        val cartMenuPrice : TextView = itemView.findViewById(R.id.tvCartMenuPrice)
        val cartMenuName : TextView = itemView.findViewById(R.id.tvCartMenuName)

        init {
            itemView.findViewById<Button>(R.id.btnDltMenuCart).setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = bindingAdapterPosition
            if (position!= RecyclerView.NO_POSITION) {
                myListener.onItemClick(cartList[position])
            }
        }
    }
}