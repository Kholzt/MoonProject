package com.example.moonproject.auth

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moonproject.R
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {
    private lateinit var birthdateEditText: EditText
    private lateinit var fullnameEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var genderSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        // Initialize EditTexts and Spinner
        birthdateEditText = findViewById(R.id.birthdate)
        fullnameEditText = findViewById(R.id.fullname)
        usernameEditText = findViewById(R.id.username)
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        confirmPasswordEditText = findViewById(R.id.confirm_password)
        phoneEditText = findViewById(R.id.phone)
        addressEditText = findViewById(R.id.address)
        genderSpinner = findViewById(R.id.gender)

        // Set up the EditText for birthdate
        birthdateEditText.setOnClickListener {
            showDatePickerDialog()
        }

        // Setup the gender spinner
        setupGenderSpinner()

        // Setup the register button
        val registerButton: Button = findViewById(R.id.registerButton)
        registerButton.setOnClickListener {
            handleRegister()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupGenderSpinner() {
        val genders = arrayOf("Select Gender", "Male", "Female",) // Placeholder at the top
        val adapter = ArrayAdapter(this, R.layout.spinner_item, genders)
        genderSpinner.adapter = adapter
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                birthdateEditText.setText(formattedDate)
            }, year, month, day)

        datePickerDialog.show()
    }

    private fun handleRegister() {
        // Validate inputs
        val fullname = fullnameEditText.text.toString()
        val username = usernameEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        val confirmPassword = confirmPasswordEditText.text.toString()
        val phone = phoneEditText.text.toString()
        val address = addressEditText.text.toString()
        val birthdate = birthdateEditText.text.toString()
        val gender = genderSpinner.selectedItem.toString()


        if (gender == "Select Gender") {
            // Show an error message (e.g., Toast or Snackbar)
            Toast.makeText(this, "Please select your gender", Toast.LENGTH_SHORT).show()
            return
        }
        if (fullname.isEmpty() || username.isEmpty() || email.isEmpty() ||
            password.isEmpty() || confirmPassword.isEmpty() || phone.isEmpty() ||
            address.isEmpty() || birthdate.isEmpty()
        ) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        // Start LoginActivity with data
        val intent = Intent(this, LoginActivity::class.java).apply {
            putExtra("FULLNAME", fullname)
            putExtra("USERNAME", username)
            putExtra("EMAIL", email)
            putExtra("PASSWORD", password)
            putExtra("PHONE", phone)
            putExtra("ADDRESS", address)
            putExtra("BIRTHDATE", birthdate)
            putExtra("GENDER", gender)
        }
        startActivity(intent)
    }
}
