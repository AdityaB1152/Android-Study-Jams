package com.godspeed.food_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.godspeed.food_app.databinding.ActivityCheckoutBinding

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
       binding = ActivityCheckoutBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}