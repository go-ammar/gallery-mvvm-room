package com.example.galleryapplication.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.galleryapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.bottom_nav)
        navView.itemIconTintList = null


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        navController = navHostFragment.navController

// Setup the bottom navigation view with navController
        navView.setupWithNavController(navController)

        // Setup the ActionBar with navController and 3 top level destinations
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.savedFragment
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}