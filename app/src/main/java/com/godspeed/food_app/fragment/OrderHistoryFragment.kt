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
import com.godspeed.food_app.MainActivity
import com.godspeed.food_app.OrderHistoryAdapter
import androidx.appcompat.app.AppCompatActivity
import com.godspeed.food_app.R
import com.godspeed.food_app.data.OrderHistoryItem
import com.godspeed.food_app.databinding.FragmentOrderHistoryBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.log

val db = Firebase.firestore

class OrderHistoryFragment : Fragment(R.layout.fragment_order_history) {
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
        db.collection("Orders").get().addOnSuccessListener {
                result->
            for(document in result){
                var orditems=""
                var map = document.data["items"] as HashMap<*, *>
                for((k, v) in map){
                    orditems = v.toString() + "\n" + orditems
                }
                orderarraylist.add(OrderHistoryItem(document.id, document.data["date_placed"] as String, document.data["status"] as String, document.data["price"] as Long, document.data["order_no"] as Long, orditems))
                Log.d("TAG", "${document.id} => ${document.data["price"]}")
            }
            val adapter = OrderHistoryAdapter(orderarraylist)
            orderhistrecycler.adapter = adapter
        }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)
//                Toast.makeText(this,"Error getting documents: ", Toast.LENGTH_SHORT).show()
}
        orderarraylist= arrayListOf<OrderHistoryItem>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}