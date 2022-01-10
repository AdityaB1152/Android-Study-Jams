package com.godspeed.food_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.godspeed.food_app.databinding.ActivityOwnerBinding
import com.godspeed.food_app.fragment.OwnerOrderFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class OwnerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_owner)

        val menuFragment=manu_fragment()
        val orderFragment=OwnerOrderFragment()

        setCurrentFragment(menuFragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationViewOwner)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.manu_fragment->setCurrentFragment(menuFragment)
                R.id.order_fragment->setCurrentFragment(orderFragment)

            }
            true
        }

    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment_owner,fragment)
            commit()
        }
}