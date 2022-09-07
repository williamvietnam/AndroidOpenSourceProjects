package com.android.vncalling.ui.features.welcome

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.android.vncalling.base.BaseActivity
import com.android.vncalling.databinding.ActivityWelcomeBinding
import com.android.vncalling.ui.features.container.MainActivity
import com.android.vncalling.ui.features.login.LoginActivity
import com.android.vncalling.utilities.Constants


class WelcomeActivity : BaseActivity<ActivityWelcomeBinding, WelcomeViewModel>() {

    private var count: Int = 0

    private val handler: Handler = Handler(Looper.getMainLooper())
    private val runnable = Runnable {
        if (binding.viewPager.currentItem == count - 1) {
            binding.viewPager.currentItem = 0
        } else {
            binding.viewPager.currentItem = binding.viewPager.currentItem + 1
        }
    }

    override fun createViewModel(): WelcomeViewModel =
        ViewModelProvider(this)[WelcomeViewModel::class.java]

    override fun getViewBinding(): ActivityWelcomeBinding {
        return ActivityWelcomeBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        this.count = viewModel.getWelcomeList().size
        val welcomeAdapter = WelcomeAdapter(viewModel.getWelcomeList())
        binding.viewPager.adapter = welcomeAdapter
        binding.indicator.setViewPager(binding.viewPager)
        setupViewpager()
        binding.btnNext.visibility = View.INVISIBLE

        binding.btnNext.setOnClickListener {
            openNextScreen(viewModel.handlerNextActivity())
        }

        binding.btnSkip.setOnClickListener {
            openNextScreen(viewModel.handlerNextActivity())
        }
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 1500)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    private fun setupViewpager() {
        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 1500)
                if (position == count - 1) {
                    binding.btnNext.visibility = View.VISIBLE
                    binding.btnSkip.visibility = View.INVISIBLE
                } else {
                    binding.btnNext.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun openNextScreen(screen: String) {
        if (screen == Constants.MAIN_ACTIVITY) {
            startActivity(Intent(this, MainActivity::class.java))
        } else if (screen == Constants.LOGIN_ACTIVITY) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()
    }
}