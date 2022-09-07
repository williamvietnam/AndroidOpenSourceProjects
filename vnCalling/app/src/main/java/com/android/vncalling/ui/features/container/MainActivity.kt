package com.android.vncalling.ui.features.container

import android.content.Intent
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.android.vncalling.R
import com.android.vncalling.base.BaseActivity
import com.android.vncalling.databinding.ActivityMainBinding
import com.android.vncalling.ui.features.login.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainView {

    companion object {
        private val activity: MainActivity = MainActivity()

        fun getInstance(): MainActivity {
            return activity
        }
    }

    override fun createViewModel(): MainViewModel =
        ViewModelProvider(this)[MainViewModel::class.java]

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        val navHostFragment: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            .setupWithNavController(navController)
    }

    override fun getMainViewModel(): MainViewModel {
        return this.viewModel
    }

    override fun hideBottomNavigationView(isHidden: Boolean) {
        if (isHidden) {
            this.binding.bottomNavigationView.visibility = GONE
        } else {
            this.binding.bottomNavigationView.visibility = VISIBLE
        }
    }

    override fun openLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}