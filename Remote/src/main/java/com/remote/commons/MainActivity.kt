package com.remote.commons

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.remote.brands.lg.LGActivity
import com.remote.brands.panasonic.PanasonicActivity
import com.remote.brands.samsung.SamsungActivity
import com.remote.brands.sony.container.SonyActivity
import com.remote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.buttonLG.setOnClickListener {
            startActivity(Intent(this, LGActivity::class.java))
            finish()
        }

        binding.buttonSony.setOnClickListener {
            startActivity(Intent(this, SonyActivity::class.java))
        }

        binding.buttonSamsung.setOnClickListener {
            startActivity(Intent(this, SamsungActivity::class.java))
        }

        binding.buttonPanasonic.setOnClickListener {
            startActivity(Intent(this, PanasonicActivity::class.java))
        }
    }
}