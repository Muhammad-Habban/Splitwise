package com.example.splitwise

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class WhiteboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_whiteboard)
        val crossButton : ImageView = findViewById(R.id.crossButton)
        crossButton.setOnClickListener{
            finish()
        }
    }

}