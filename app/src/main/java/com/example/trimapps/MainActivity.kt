package com.example.trimapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.trimapps.Home.HomeFragment
import com.example.trimapps.ProfileFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment(HomeFragment.newInstance())
        val navigation = findViewById<MeowBottomNavigation>(R.id.navigation)
        navigation.add(MeowBottomNavigation.Model(1, R.drawable.baseline_home_24))
        navigation.add(MeowBottomNavigation.Model(2, R.drawable.baseline_person_24))

        navigation.setOnClickMenuListener {
            when (it.id) {
                1 -> setFragment(HomeFragment.newInstance())
                2 -> setFragment(ProfileFragment.newInstance())
                else -> ""
            }
        }
        navigation.show(1)
    }
    fun setFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout,fragment,"mainActivity")
            .commit()
    }
}
