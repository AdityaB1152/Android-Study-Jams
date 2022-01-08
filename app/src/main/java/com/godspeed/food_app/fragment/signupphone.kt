package com.godspeed.food_app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.godspeed.food_app.R
import com.godspeed.food_app.databinding.FragmentSignupphoneBinding

class signupphone : Fragment(R.layout.fragment_signupphone) {
    private lateinit var binding: FragmentSignupphoneBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupphoneBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.enterotp.setOnClickListener {
            R.layout.fragment_otp
        }
    }
}