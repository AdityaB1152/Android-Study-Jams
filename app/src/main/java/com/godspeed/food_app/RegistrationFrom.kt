package com.godspeed.food_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.godspeed.food_app.adapter.Menu

class RegistrationFrom : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_from)

        val button = findViewById<Button>(R.id.submit)

        button.setOnClickListener {
            val intent = Intent(this, Menuorder::class.java)
            intent.putExtra("newname", button.text.toString())
            startActivity(intent)
        }
    }
}