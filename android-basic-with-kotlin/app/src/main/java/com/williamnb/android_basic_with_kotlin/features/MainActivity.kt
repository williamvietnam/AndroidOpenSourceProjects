package com.williamnb.android_basic_with_kotlin.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.williamnb.android_basic_with_kotlin.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root;
        setContentView(view);
    }
}