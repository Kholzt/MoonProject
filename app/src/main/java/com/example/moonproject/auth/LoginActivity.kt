package com.example.moonproject.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moonproject.HomeActivity
import com.example.moonproject.R

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)

        // Check if the user is already logged in
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        if (isLoggedIn) {
            // If logged in, redirect to HomeActivity
            startActivity(Intent(this, HomeActivity::class.java))
            finish() // Close the LoginActivity
            return
        }

        // Adjust for window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retrieve layout components
        val register: TextView = findViewById(R.id.registerLink)
        val login: TextView = findViewById(R.id.loginBtn)
        val emailInput: EditText = findViewById(R.id.email)
        val passwordInput: EditText = findViewById(R.id.password)

        // Retrieve data from Intent (sent from RegisterActivity)
        val registeredEmail = intent.getStringExtra("EMAIL")
        val registeredPassword = intent.getStringExtra("PASSWORD")

        // Pre-fill email if available
        registeredEmail?.let {
            emailInput.setText(it)
        }

        // Handle Register button click
        register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Handle Login button click
        login.setOnClickListener {
            val inputEmail = emailInput.text.toString()
            val inputPassword = passwordInput.text.toString()

            // Check if login details match registered data
            if (inputEmail == registeredEmail && inputPassword == registeredPassword) {
                // Save login details to SharedPreferences
                sharedPreferences.edit().apply {
                    putString("email", inputEmail)
                    putBoolean("isLoggedIn", true)
                    apply() // Asynchronous save
                }

                // Show success message and navigate to HomeActivity
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
                finish() // Close the LoginActivity
            } else {
                // Show error message
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
