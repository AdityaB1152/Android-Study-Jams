package com.godspeed.food_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.food_app.databinding.FragmentManuFragmentBinding


class manu_fragment : Fragment() {

    private lateinit var binding: FragmentManuFragmentBinding
    private lateinit var newRecyclerview: RecyclerView
    private lateinit var menuArrayList:ArrayList<News>
    private lateinit var imageid:Array<Int>
    private lateinit var foodname:Array<String>
    private lateinit var cost:Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentManuFragmentBinding.inflate(layoutInflater)
        newRecyclerview = binding.recyclerView
        newRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        newRecyclerview.setHasFixedSize(true)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageid= arrayOf   (
            R.drawable.rice,
            R.drawable.rice,
            R.drawable.rice,
            R.drawable.rice,
            R.drawable.pavbhaji,
            R.drawable.paneer,
            R.drawable.rice,
            R.drawable.rice,
            R.drawable.sandwich,
            R.drawable.samosa,
            R.drawable.vada,
            R.drawable.tea

        )
        foodname= arrayOf(
            "Kaju Masala",
            "Roti",
            "Bhaji",
            "Rice",
            "Pavbhaji",
            "Panerr masala",
            "Coffee",
            "dosa",
            "sandwitch",
            "samosa",
            "vada",
            "tea"

        )

        cost= arrayOf(
            "COST - 60",
            "COST - 10",
            "COST - 40",
            "COST - 20",
            "COST - 50",
            "COST - 70",
            "COST - 30",
            "COST - 60",
            "COST - 30",
            "COST - 30",
            "COST - 15",
            "COST - 10"

        )

        menuArrayList = arrayListOf()
        getMenuData()




    }

    private fun getMenuData(){

        for ( i in imageid.indices){
            val menu = News(imageid[i],foodname[i],cost[i])
            menuArrayList.add(menu)

        }
        newRecyclerview.adapter=MyAdapter(menuArrayList)
    }
}