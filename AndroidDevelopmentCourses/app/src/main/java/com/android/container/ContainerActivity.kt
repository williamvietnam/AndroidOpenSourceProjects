package com.android.container

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.android.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ContainerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(this.binding.navHostFragment.id) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(this.binding.bottomNavigation.id)
            .setupWithNavController(navController)
    }
}