package com.android.api.retrofit.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.api.databinding.ActivityRetrofitBinding

class RetrofitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRetrofitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}