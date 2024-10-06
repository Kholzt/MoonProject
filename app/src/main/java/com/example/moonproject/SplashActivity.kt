package com.example.moonproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moonproject.auth.LoginActivity
import java.util.Timer
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mengaktifkan edge-to-edge mode
        enableEdgeToEdge()

        // Menyembunyikan ActionBar
        supportActionBar?.hide()

        // Mengatur layout activity
        setContentView(R.layout.activity_splash)

        // Menambahkan listener untuk inset sistem (system bars) pada view dengan ID "main"
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        // Delay 2 detik untuk berpindah ke LoginActivity
        Handler(Looper.getMainLooper()).postDelayed({
            // Intent untuk pindah ke LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Selesai dengan SplashActivity sehingga tidak bisa kembali
        }, 3000) // 6000 milliseconds = 3 seconds
    }
}
