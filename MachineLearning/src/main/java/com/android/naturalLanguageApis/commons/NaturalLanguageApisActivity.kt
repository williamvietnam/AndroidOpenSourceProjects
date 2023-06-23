package com.android.naturalLanguageApis.commons

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.android.R
import com.android.databinding.ActivityNaturalLanguageApisBinding

class NaturalLanguageApisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNaturalLanguageApisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNaturalLanguageApisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // init navigation component
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as NavHostFragment
        val navController = navHostFragment.navController
    }
}