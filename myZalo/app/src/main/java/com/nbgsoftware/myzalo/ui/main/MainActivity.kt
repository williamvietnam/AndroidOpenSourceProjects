package com.nbgsoftware.myzalo.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nbgsoftware.myzalo.R
import com.nbgsoftware.myzalo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}