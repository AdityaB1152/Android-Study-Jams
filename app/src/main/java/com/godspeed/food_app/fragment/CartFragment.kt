package com.godspeed.food_app.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.godspeed.food_app.R
import com.godspeed.food_app.adapter.CartAdapter
import com.godspeed.food_app.data.Cart
import com.godspeed.food_app.databinding.FragmentCartBinding
import com.godspeed.food_app.viewmodel.CartViewModel

class CartFragment : Fragment(R.layout.fragment_cart),CartAdapter.OnItemClickListener {

    private lateinit var binding : FragmentCartBinding
    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentCartBinding.inflate(layoutInflater)
        cartAdapter = CartAdapter(requireContext(), ArrayList<Cart>(),this)
        binding.rvCart.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartAdapter
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("PRI","working")
        cartAdapter = CartAdapter(requireContext(), ArrayList<Cart>(),this)
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        cartViewModel.getAllMenu(requireContext())?.observe(viewLifecycleOwner, Observer {

            cartAdapter.setData(it as ArrayList<Cart>)
        })
    }

    override fun onItemClick(cart: Cart) {
        cartViewModel.deleteMenu(requireContext(),cart)
    }


}