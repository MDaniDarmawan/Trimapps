package com.example.trimapps

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.trimapps.Home.HomeFragment
import com.example.trimapps.databinding.ActivityMainBinding
import com.example.trimapps.fragments.ProfileFragment
import com.example.trimapps.fragments.favoriteFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    protected val home: Int = 1
    protected val profile: Int = 2
    private lateinit var auth: FirebaseAuth
    lateinit var database: FirebaseDatabase
    val fragmentprofil: Fragment = ProfileFragment()
    val fragmenthome: Fragment = HomeFragment()
    val fragmentfavorit: Fragment = favoriteFragment()
    val fm: FragmentManager = supportFragmentManager
    var active: Fragment = fragmenthome

    private lateinit var buttomNavigationView: BottomNavigationView
    private lateinit var menu: Menu
    lateinit var menuItem: MenuItem
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        //ini bisa dihapus juga kalo error

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()



    @SuppressLint("MissingInflated", "LocalSuppress")
    val bottomNavigation = findViewById<MeowBottomNavigation>(R.id.meowBottomNavigation)
    bottomNavigation.add(MeowBottomNavigation.Model(home, R.drawable.baseline_home_24))
    bottomNavigation.add(MeowBottomNavigation.Model(profile, R.drawable.baseline_person_24))


        buttomNavigationView.setOnNavigationItemReselectedListener {
            when (it.itemId){
                R.id.ic_home  -> {
                    callFragment(0,fragmenthome)
                }
                R.id.profil -> {
                    callFragment(1,fragmentprofil)
                }
            }
            false
        }
    }

    private fun callFragment(index : Int,fragment: Fragment)
    {
        menuItem = menu.getItem(index)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}