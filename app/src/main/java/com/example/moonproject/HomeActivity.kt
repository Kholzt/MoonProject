package com.example.moonproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.moonproject.HomeFragment.KontakFragment
import com.example.moonproject.HomeFragment.ListMahasiswaFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navigation: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        //fragment default
        loadFragment(KontakFragment());

        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.kontak_nav -> {
                    loadFragment(KontakFragment())
                    true
                }
                R.id.mahasiswa_nav -> {
                    loadFragment(ListMahasiswaFragment())
                    true
                }
                else -> false
            }
        }
    }


    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, fragment)
            .commit()
    }
}