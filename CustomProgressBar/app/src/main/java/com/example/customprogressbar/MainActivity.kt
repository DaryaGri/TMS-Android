package com.example.customprogressbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customProgressBar = findViewById<MyProgressBar>(R.id.customProgressBar)
        val userEditText = findViewById<EditText>(R.id.userEditText)

        userEditText.setOnClickListener {
            customProgressBar.myValue = userEditText.text.toString().toInt()
        }
    }
}