package com.android.vncalling.ui.features.container

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.android.vncalling.R
import com.android.vncalling.base.BaseActivity
import com.android.vncalling.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainView {

    override fun createViewModel(): MainViewModel =
        ViewModelProvider(this)[MainViewModel::class.java]

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun initialize() {
        val navHostFragment: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            .setupWithNavController(navController)
    }

    override fun hideBottomNavigationView(isHidden: Boolean) {
        if (isHidden) {
            this.binding.bottomNavigationView.visibility = GONE
        } else {
            this.binding.bottomNavigationView.visibility = VISIBLE
        }
    }
}