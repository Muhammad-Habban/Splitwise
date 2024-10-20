package com.example.splitwise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.splitwise.ui.theme.SplitwiseTheme
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signInButton : Button = findViewById<Button>(R.id.btnGoogleSignIn)
        signInButton.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
             finish()
        }
        val logInButton : Button = findViewById<Button>(R.id.btnLogIn)
        logInButton.setOnClickListener{
            val intent2 = Intent(this, LoginActivity::class.java)
            startActivity(intent2)
        }
        val signupButton : Button = findViewById<Button>(R.id.btnSignUp)
        signupButton.setOnClickListener{
            val intent3 = Intent(this, SignupActivity::class.java)
            startActivity(intent3)
        }
    }
}