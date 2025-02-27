package com.example.georeminder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val createButton = findViewById<Button>(R.id.createButton)
        createButton.setOnClickListener() {onCreate()}
    }

    private fun onCreate() {
        val intent = Intent(this, CreateActivity::class.java)
        startActivity(intent)
    }
}