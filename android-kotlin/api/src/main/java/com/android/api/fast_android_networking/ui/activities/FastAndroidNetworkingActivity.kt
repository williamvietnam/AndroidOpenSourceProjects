package com.android.api.fast_android_networking.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.api.databinding.ActivityFastAndroidNetworkingBinding

class FastAndroidNetworkingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFastAndroidNetworkingBinding
    private lateinit var viewModel: FastAndroidNetworkingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFastAndroidNetworkingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(FastAndroidNetworkingViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}