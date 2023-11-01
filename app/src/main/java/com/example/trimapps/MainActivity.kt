package com.example.trimapps

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.etebarian.meowbottomnavigation.MeowBottomNavigation

class MainActivity : AppCompatActivity() {

    protected val home: Int = 1
    protected val profile: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        @SuppressLint("MissingInflated", "LocalSuppress")
        val bottomNavigation = findViewById<MeowBottomNavigation>(R.id.meowBottomNavigation)
        bottomNavigation.add(MeowBottomNavigation.Model(home, R.drawable.baseline_home_24))
        bottomNavigation.add(MeowBottomNavigation.Model(profile, R.drawable.baseline_person_24))

        bottomNavigation.setOnShowListener {
        }

        bottomNavigation.setOnClickMenuListener {
            val model = it
            val name = when (model.id) {
                home -> "Home"
                profile -> "Profile"
                else -> ""
            }
        }
    }
}
