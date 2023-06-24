package com.android.visionApis.commons

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.android.R
import com.android.databinding.ActivityVisionApisBinding

class VisionApisActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVisionApisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVisionApisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // init navigation component
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as NavHostFragment
        val navController = navHostFragment.navController
    }
}