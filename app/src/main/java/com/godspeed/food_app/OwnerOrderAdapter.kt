package com.godspeed.food_app

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.food_app.data.OwnerOrderModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class OwnerOrderAdapter(private val ctx : Context, private var orderList:ArrayList<OwnerOrderModel> ):RecyclerView.Adapter<OwnerOrderAdapter.MyViewHolder>() {

    val db = Firebase.firestore
    private val TAG = "OwnerOrderAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.owner_order_cardview, parent, false)
        Log.d(TAG, "onCreateViewHolder: length:${orderList.size}")
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val orderobj = orderList[position]
        holder.date.text = orderList[position].date
        holder.orderId.text = orderList[position].orderNo.toString()
        holder.price.text = orderList[position].price.toString()
        holder.orderitem.text = orderList[position].orderItem
        holder.name.text = orderList[position].name

        val items = arrayOf("Placed", "Ready", "Complete")
        val adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, items)
        holder.spinner.adapter = adapter
        holder.spinner.onItemSelectedListener = object :

            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val newStatus = items[p2]
                db.collection("Orders").document(orderobj.id).update("status", newStatus)
                    .addOnSuccessListener {
                        Log.d("TAG", "DocumentSnapshot successfully written!")
                        Toast.makeText(ctx, "STATUS Selected", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e -> Log.w("TAG", "Error writing document", e) }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(ctx, "Noting Selected", Toast.LENGTH_SHORT).show()
            }


        }
        holder.cancel.setOnClickListener {
            db.collection("Orders").document(orderobj.id)
                .update("status", "Cancelled")
                .addOnSuccessListener {
                    Log.d("TAG", "DocumentSnapshot successfully written!")
                    Toast.makeText(ctx, "Order Cancelled", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error writing document", e)
                }

        }
    }

    override fun getItemCount() = orderList.size


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date = itemView.findViewById<TextView>(R.id.order_date)
        val orderId = itemView.findViewById<TextView>(R.id.order_no)
        val price = itemView.findViewById<TextView>(R.id.Price)
        val orderitem = itemView.findViewById<TextView>(R.id.item_quantity)
        val name = itemView.findViewById<TextView>(R.id.name)
        val spinner = itemView.findViewById<Spinner>(R.id.spinner)
        val cancel = itemView.findViewById<Button>(R.id.cancel_button)
    }
}