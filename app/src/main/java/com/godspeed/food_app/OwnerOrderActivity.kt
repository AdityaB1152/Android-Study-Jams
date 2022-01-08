package com.godspeed.food_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.food_app.data.OwnerOrderModel

class OwnerOrderActivity : AppCompatActivity() {

    private lateinit var ownerorderRecyclerView : RecyclerView
    private lateinit var ownerorderArrayList: ArrayList<OwnerOrderModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.owner_order_recview)

        ownerorderRecyclerView = findViewById(R.id.ownerorder_recview)
        ownerorderRecyclerView.layoutManager = LinearLayoutManager(this)
        ownerorderRecyclerView.setHasFixedSize(true)


    }
}