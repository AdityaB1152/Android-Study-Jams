package com.godspeed.food_app.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.food_app.OrderHistoryAdapter
import com.godspeed.food_app.R
import com.godspeed.food_app.data.OrderHistoryItem
import com.godspeed.food_app.databinding.FragmentOrderHistoryBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class OrderHistoryFragment : Fragment(R.layout.fragment_order_history) {

    private val db = Firebase.firestore

    private lateinit var binding: FragmentOrderHistoryBinding
    private lateinit var orderarraylist: ArrayList<OrderHistoryItem>
    private lateinit var orderhistrecycler: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderHistoryBinding.inflate(layoutInflater)
        orderhistrecycler=binding.rvorderhist
        orderhistrecycler.layoutManager=LinearLayoutManager(requireContext())
        orderhistrecycler.setHasFixedSize(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orderarraylist= arrayListOf()
        db.collection("Orders").get().addOnSuccessListener {
                result->
            for(document in result){
                var orditems=""
                val map = document.data["items"] as HashMap<*, *>
                for((_, v) in map){
                    v as HashMap<*,*>
                    orditems = orditems + v["menuName"] as String + " : " + v["menuQuantity"].toString() + " : ₹" + v["menuPrice"].toString() + "\n"
                }
                val timestamp = document.data["date"] as com.google.firebase.Timestamp
                val milliseconds = timestamp.seconds * 1000 + timestamp.nanoseconds / 1000000
                val sdf = SimpleDateFormat("dd/MM/yyyy")
                val netDate = Date(milliseconds)
                val date_placed = sdf.format(netDate).toString()
                orderarraylist.add(OrderHistoryItem(document.id,
                    date_placed, document.data["status"] as String, document.data["price"] as Long, document.data["orderNum"] as Long, orditems))
                Log.d("TAG", "${document.id} => ${document.data["price"]}")
            }
            val adapter = OrderHistoryAdapter(orderarraylist)
            orderhistrecycler.adapter = adapter
        }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)
                Toast.makeText(context,"Error getting documents: ", Toast.LENGTH_SHORT).show()
            }
}
}