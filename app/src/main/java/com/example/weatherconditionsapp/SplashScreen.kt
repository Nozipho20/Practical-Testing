package com.example.weatherconditionsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashScreen : AppCompatActivity() {
    private lateinit var btnEnter: Button
    private lateinit var btnExit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.splashscreen)
        btnEnter = findViewById(R.id.btnEnter)
        btnExit = findViewById(R.id.btnExit)

        // Set up screen navigation
        btnEnter.setOnClickListener {
            val intent = Intent(this, MainScreen::class.java)
            startActivity(intent)
        }


        btnExit.setOnClickListener {
            finishAffinity() // Safely closes the entire application
        }
    }
}
