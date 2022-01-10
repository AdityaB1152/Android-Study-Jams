package com.godspeed.food_app.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.food_app.MainActivity
import com.godspeed.food_app.OwnerOrderAdapter
import com.godspeed.food_app.R
import com.godspeed.food_app.data.OwnerOrderModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.log
import com.godspeed.food_app.databinding.FragmentOwnerOrderBinding


class OwnerOrderFragment : Fragment(R.layout.fragment_owner_order) {

    private val db = Firebase.firestore
    private lateinit var binding: FragmentOwnerOrderBinding
    private lateinit var orderarraylist: ArrayList<OwnerOrderModel>
    private lateinit var ownerorder: RecyclerView


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val dropdown = itemView.findViewById<Spinner>(R.id.spinner)
        val items = arrayOf("1", "2", "three")
        //val adapter = ArrayAdapter(context,android.R.layout.simple_spinner_dropdown_item, items)
        //dropdown.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOwnerOrderBinding.inflate(layoutInflater)
        ownerorder=binding.ownerrv
        ownerorder.layoutManager=LinearLayoutManager(requireContext())
        ownerorder.setHasFixedSize(true)
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
                orderarraylist.add(OwnerOrderModel(document.id,document.data["date_placed"] as String,document.data["order_no"] as Long, document.data["price"] as Long,  orditems, document.data["name"] as String))
                Log.d("TAG", "${document.id} => ${document.data["price"]}")
            }
            val adapter = OwnerOrderAdapter(requireContext(),orderarraylist)
            ownerorder.adapter = adapter
        }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)

//                Toast.makeText(this,"Error getting documents: ", Toast.LENGTH_SHORT).show()
            }
        orderarraylist = arrayListOf<OwnerOrderModel>()
//        getorderhist()
    }
}