package com.godspeed.food_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import com.godspeed.food_app.databinding.ActivityMainBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"
    private val auth = FirebaseAuth.getInstance()
    private var storedVerificationId: String = ""
    private lateinit var binding: ActivityMainBinding
    private val db = Firebase.firestore

    override fun onStart() {
        super.onStart()
        binding.layoutLoadingProfile.visibility = View.VISIBLE
        binding.authCardView.visibility = View.GONE
        if(auth.currentUser != null){
            db.collection("Profiles").document(auth.currentUser!!.uid).get()
                .addOnCompleteListener{task->
                    if(task.result?.exists() == true){
                        if(task.result!!["role"] as String == "Customer"){
                            val intent = Intent(this, MainActivity3::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            val intent = Intent(this, OwnerActivity::class.java)
                            startActivity(intent)
                            finish()
                        }

                    } else {
                        val intent = Intent(this, MainActivity2::class.java)
                        startActivity(intent)
                        finish()
                    }

                }
        } else {
            binding.layoutLoadingProfile.visibility = View.VISIBLE
            binding.authCardView.visibility = View.GONE
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.getotp.setOnClickListener{sendOtp()}
        binding.verifotp.setOnClickListener{verifyOtp()}
    }

    private fun sendOtp(){
        if (binding.enterphone.text.isEmpty() || binding.enterphone.text.length < 10){
            Toast.makeText(this, "Please enter valid phone number!", Toast.LENGTH_SHORT).show()
            return
        }
        binding.authProgress.visibility = View.VISIBLE
        val phoneNumber = "+91 " + binding.enterphone.text.toString()

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
    private fun verifyOtp(){
        if (binding.enterotp2.text.isEmpty() || binding.enterotp2.text.length < 6){
            Toast.makeText(this, "Please enter valid 6 digit OTP!", Toast.LENGTH_SHORT).show()
            return
        }
        val otpNumber = binding.enterotp2.text.toString()
        val credential = PhoneAuthProvider.getCredential(storedVerificationId, otpNumber)
        signInWithPhoneAuthCredential(credential)
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d(TAG, "onVerificationCompleted: $credential")
            binding.authProgress.visibility = View.GONE
            binding.layoutPhone.visibility = View.GONE
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Log.w(TAG, "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                Toast.makeText(applicationContext, "Invalid Request! Contact Developer!", Toast.LENGTH_SHORT).show()
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                Toast.makeText(applicationContext, "SMS Quota Reached! Contact Developer!", Toast.LENGTH_SHORT).show()
            }
            binding.authProgress.visibility = View.GONE
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Log.d(TAG, "onCodeSent:$verificationId")
//            Toast.makeText(RequireContext(), "OTP Sent Successfully!", Toast.LENGTH_SHORT).show()
            // Save verification ID and resending token so we can use them later
            storedVerificationId = verificationId
//            resendToken = token
            binding.authProgress.visibility = View.GONE
            binding.layoutPhone.visibility = View.GONE
            binding.layoutOtp.visibility = View.VISIBLE
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(this, "Invalid code, Try Again!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
                    }
                }
                binding.authProgress.visibility = View.GONE

            }
    }
}