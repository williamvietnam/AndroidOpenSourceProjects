package com.android.container

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.android.R
import com.android.base.BaseActivity
import com.android.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun createViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): MainViewModel {
        return ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun initializeViews() {
        // init navigation component
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as NavHostFragment
        val navController = navHostFragment.navController

        // check create contactsDatabase from json
        viewModel.saveContactsListFromJsonToDatabase(this)
    }

    override fun initializeEvents() {

    }
}