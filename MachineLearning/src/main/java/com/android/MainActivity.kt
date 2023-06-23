package com.android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.android.databinding.ActivityMainBinding
import com.android.naturalLanguageApis.commons.NaturalLanguageApisActivity
import com.android.visionApis.commons.VisionApisActivity

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.visionApis.setOnClickListener(this)
        binding.naturalLanguageApis.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.visionApis.id -> {
                startActivity(Intent(this, VisionApisActivity::class.java))
            }

            binding.naturalLanguageApis.id -> {
                startActivity(Intent(this, NaturalLanguageApisActivity::class.java))
            }
        }
    }
}