package com.godspeed.food_app.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.food_app.R
import com.godspeed.food_app.adapter.CartAdapter
import com.godspeed.food_app.adapter.Menu
import com.godspeed.food_app.adapter.MenuAdapter
import com.godspeed.food_app.data.Cart
import com.godspeed.food_app.databinding.FragmentMenuBinding
import com.godspeed.food_app.viewmodel.CartViewModel


class MenuFragment : Fragment(R.layout.fragment_menu) , MenuAdapter.OnItemClickListener{

    private lateinit var binding: FragmentMenuBinding
    private lateinit var menuArrayList: ArrayList<Menu>
    private lateinit var menuRecyclerView: RecyclerView
    lateinit var imageId: Array<Int>
    lateinit var name: Array<String>
    lateinit var price: Array<Int>
    private lateinit var cartViewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater)
        menuRecyclerView = binding.rvMenuList
        menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        menuRecyclerView.setHasFixedSize(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        imageId = arrayOf(
            R.drawable.menu_food80,
            R.drawable.chapati,
            R.drawable.bhaji,
            R.drawable.rice,
            R.drawable.pavbhaji,
            R.drawable.paneer,
            R.drawable.coffee,
            R.drawable.menu_food80,
            R.drawable.sandwich,
            R.drawable.samosa,
            R.drawable.vada,
            R.drawable.tea
        )

        name = arrayOf(
            "Kaju Masala",
            "Roti",
            "Bhaji",
            "Rice",
            "PavBhaji",
            "Paneer Masala",
            "Coffee",
            "Dosa",
            "Sandwitch",
            "Samosa",
            "Vada",
            "Tea"
        )

        price = arrayOf(
            60,10,40,20,50,70,30,60,30,30,15,10
        )





        menuArrayList = arrayListOf<Menu>()
        getMenuData()


    }

    private fun getMenuData(){

        for ( i in imageId.indices){
            val menu = Menu(imageId[i],name[i],price[i],1)
            menuArrayList.add(menu)

        }
        menuRecyclerView.adapter = MenuAdapter(requireContext(),this,menuArrayList)
    }

    override fun onItemClick(position: Int, qty : Int) {
        Toast.makeText(requireContext(), "Added to Cart", Toast.LENGTH_SHORT).show()
        saveDataInRoomDataBase(position,qty)

    }

    override fun onCreateOptionsMenu(menu: android.view.Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.cart_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.cart ->{
                findNavController().navigate(R.id.action_menuFragment_to_cartFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveDataInRoomDataBase(position: Int, qty: Int) {
        val cartMenuImage = imageId[position]
        val cartMenuName  = name[position]
        val cartMenuPrice = price[position]
        val cartMenuQty = qty
        cartViewModel.addMenuToCart(requireContext(), Cart(cartMenuImage,cartMenuName,cartMenuPrice,cartMenuQty))

    }
}