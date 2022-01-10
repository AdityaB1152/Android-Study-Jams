package com.godspeed.food_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.godspeed.food_app.databinding.ActivityMain2Binding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity2 : AppCompatActivity() {

    private val TAG = "MainActivity2"
    private val db = Firebase.firestore
    private lateinit var binding: ActivityMain2Binding
    lateinit var radioBtn:RadioButton;

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.btnSubmitProfile.setOnClickListener {
            submitProfile()
        }

        binding.radioGroupRole.setOnCheckedChangeListener { _, checkedId ->
            radioBtn = findViewById(checkedId)
            if(radioBtn.text.equals("Customer")){
                binding.etBranch.visibility = View.VISIBLE
                binding.etRoll.visibility = View.VISIBLE
            } else {
                binding.etBranch.visibility = View.GONE
                binding.etRoll.visibility = View.GONE
            }
        }
    }

    private fun submitProfile() {
        val profileMap:HashMap<String, String> = HashMap()
        if(binding.etPersonName.text.isEmpty()){
            binding.etPersonName.error = "Please enter your name!"
            return
        }

        radioBtn = findViewById(binding.radioGroupRole.checkedRadioButtonId)
        if(radioBtn.text.equals("Customer")){
            if(binding.etBranch.text.isEmpty()){
                binding.etBranch.error = "Branch Name cannot be empty!"
                return
            }
            if(binding.etRoll.text.isEmpty()){
                binding.etRoll.error = "Roll Number cannot be empty!"
                return
            }
            profileMap["branchName"] = binding.etPersonName.text.toString()
            profileMap["rollNumber"] = binding.etPersonName.text.toString()
            profileMap["role"] = "Customer"
        } else {
            profileMap["role"] = "Owner"
        }

        profileMap["personName"] = binding.etPersonName.text.toString()
        profileMap["phoneNumber"] = Firebase.auth.currentUser?.phoneNumber.toString()

        db.collection("Profiles").document(Firebase.auth.currentUser?.uid.toString())
            .set(profileMap).addOnCompleteListener{task->
                if (task.isSuccessful){
                    if(profileMap["role"] == "Customer"){
                        val intent = Intent(this, MainActivity3::class.java)
                        startActivity(intent)
                    } else {
                        val intent = Intent(this, MainActivity3::class.java)
                        startActivity(intent)
                    }

                } else {
                    Log.d(TAG, "submitProfile: Error saving profile! ", task.exception)
                    Toast.makeText(applicationContext, "Something went wrong!", Toast.LENGTH_SHORT).show()
                }
            }


    }
}