package com.godspeed.food_app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.godspeed.food_app.R
import com.godspeed.food_app.databinding.FragmentOtpBinding

class otp : Fragment(R.layout.fragment_otp) {
    private lateinit var binding: FragmentOtpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOtpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.verifyotp.setOnClickListener {
            R.layout.fragment_registration
        }
    }
}