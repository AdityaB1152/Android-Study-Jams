package com.godspeed.food_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.godspeed.food_app.databinding.ActivityOwnerBinding

class OwnerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOwnerBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_owner)

        binding = ActivityOwnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_owner) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
        binding.bottomNavigationViewOwner.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id.menuFragment -> binding.bottomNavigationView.visibility = View.VISIBLE
//                R.id.orderHistoryFragment -> binding.bottomNavigationView.visibility = View.VISIBLE
////                else -> binding.bottomNavigationView.visibility = View.GONE
//            }
        }
    }
}