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
import com.godspeed.food_app.OwnerOrderAdapter
import com.godspeed.food_app.R
import com.godspeed.food_app.data.OwnerOrderModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import com.godspeed.food_app.databinding.FragmentOwnerOrderBinding
import java.text.SimpleDateFormat
import java.util.*

class OwnerOrderFragment : Fragment(R.layout.fragment_owner_order) {

    private val TAG = "OwnerOrderFragment"
    private val db = Firebase.firestore
    private lateinit var binding: FragmentOwnerOrderBinding
    private var orderarraylist: ArrayList<OwnerOrderModel> = arrayListOf()
    private lateinit var ownerOrdersRV: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOwnerOrderBinding.inflate(layoutInflater)
        ownerOrdersRV=binding.ownerrv
        ownerOrdersRV.layoutManager=LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db.collection("Orders").get().addOnSuccessListener { result ->
            for (document in result) {
                var orderItems = ""
                val map = document.data["items"] as HashMap<*, *>
                for ((_, v) in map) {
                    v as HashMap<*, *>
                    orderItems =
                        orderItems + v["menuName"] as String + " : " + v["menuQuantity"].toString() + " : ₹" + v["menuPrice"].toString() + "\n"
                }
                val timestamp = document.data["date"] as com.google.firebase.Timestamp
                val milliseconds = timestamp.seconds * 1000 + timestamp.nanoseconds / 1000000
                val sdf = SimpleDateFormat("dd/MM/yyyy")
                val netDate = Date(milliseconds)
                val datePlaced = sdf.format(netDate).toString()
                Log.d("TAG", "${document.id} => ${document.data["price"]}")
                orderarraylist.add(
                    OwnerOrderModel(
                        document.id,
                        datePlaced,
                        document.data["orderNum"] as Long,
                        document.data["price"] as Long,
                        orderItems,
                        document.data["name"] as String
                    )
                )
            }
            Log.d(TAG, "onViewCreated: length:${orderarraylist.size}")
            val adapter = OwnerOrderAdapter(requireContext(), orderarraylist)
            ownerOrdersRV.adapter = adapter
        }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)
                Toast.makeText(context,"Error getting documents: ", Toast.LENGTH_SHORT).show()
            }
    }
}