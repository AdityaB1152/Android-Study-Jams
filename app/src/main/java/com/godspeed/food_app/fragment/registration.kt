package com.godspeed.food_app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.food_app.R
import com.godspeed.food_app.data.OrderHistoryItem
import com.godspeed.food_app.databinding.FragmentOrderHistoryBinding
import com.godspeed.food_app.databinding.FragmentOtpBinding
import com.godspeed.food_app.databinding.FragmentRegistrationBinding

class registration : Fragment(R.layout.fragment_registration) {
    private lateinit var binding: FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(layoutInflater)
        return binding.root
    }
}