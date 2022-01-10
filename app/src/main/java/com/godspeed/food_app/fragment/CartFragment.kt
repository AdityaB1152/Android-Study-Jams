package com.godspeed.food_app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.food_app.R
import com.godspeed.food_app.adapter.CartAdapter
import com.godspeed.food_app.data.Cart
import com.godspeed.food_app.data.OrderClass
import com.godspeed.food_app.databinding.FragmentCartBinding
import com.godspeed.food_app.viewmodel.CartViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class CartFragment : Fragment(R.layout.fragment_cart),CartAdapter.OnItemClickListener {

    private lateinit var binding : FragmentCartBinding
    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartViewModel: CartViewModel
    private lateinit var cartRecyclerView: RecyclerView
    private lateinit var database:FirebaseFirestore
    private lateinit var list:ArrayList<Cart>
    private lateinit var auth:FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentCartBinding.inflate(layoutInflater)
        database = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        cartRecyclerView = binding.rvCart
        cartRecyclerView.setHasFixedSize(true)
        cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        list = ArrayList();
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseFirestore.getInstance()

        cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)
        cartViewModel.getAllMenu(requireActivity().applicationContext)?.observe(requireActivity(), {
            val list:ArrayList<Cart> = it as ArrayList<Cart>
            cartAdapter = CartAdapter(context, list,this)
            cartRecyclerView.adapter = cartAdapter
            if(list.isEmpty()){
                Toast.makeText(context , "Please add Items in your Cart", Toast.LENGTH_SHORT).show()
                return@observe
            }
            binding.placeOrder.setOnClickListener{
                if(list.isEmpty()){
                    Toast.makeText(context , "Please add Items in your Cart", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val profileRef:DocumentReference = database.collection("Profiles").document(auth.uid.toString())
                profileRef.get().addOnSuccessListener { documentSnap ->
                    if(documentSnap.exists()){
                        val name:String = documentSnap.data?.getValue("personName").toString()
                        val items:HashMap<String , Cart> = HashMap()
                        var totalPrice = 0
                        for(cart:Cart in list){
                            totalPrice += cart.menuPrice
                            items[cart.menuName] = cart
                        }
                        val orderRef:DocumentReference = database.collection("Data").document("mCp2PajRexjDcNHaGUmY")

                        orderRef.get().addOnSuccessListener { docSnap ->
                            var orderNum:String = docSnap.data?.getValue("total_orders").toString()
                            val date = Date()
                            val orderClass = OrderClass(name , totalPrice , items , orderNum.toInt()+1 , "Placed", date)
                            database.collection("Orders").add(orderClass).addOnSuccessListener {
                                for(cart:Cart in list){
                                    val total_orders:HashMap<String , Int> = HashMap()
                                    total_orders.put("total_orders",orderNum.toInt()+1)
                                    orderRef.set(total_orders)
                                    cartViewModel.deleteMenu(requireContext()  , cart);
                                    Toast.makeText(context , "Order Placed" , Toast.LENGTH_SHORT).show()
                                }
                            }
                        }

                    }



                }
            }
            binding.emptyCart.setOnClickListener{
                if(list.isEmpty()){
                    Toast.makeText(context , "Cart is Already Empty!" , Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                for(cart:Cart in list){
                    cartViewModel.deleteMenu(requireContext()  ,cart)
                }
                Toast.makeText(context , "Cart is Empty" , Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onItemClick(cart: Cart) {
        cartViewModel.deleteMenu(requireContext(),cart)
    }


}