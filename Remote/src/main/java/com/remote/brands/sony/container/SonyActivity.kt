package com.remote.brands.sony.container

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.remote.databinding.ActivitySonyBinding


class SonyActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySonyBinding
    private lateinit var viewModel: SonyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[SonyViewModel::class.java]
        binding = ActivitySonyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSetIpAndPSK.setOnClickListener(this)
        binding.buttonPowerOn.setOnClickListener(this)
        binding.buttonPowerOff.setOnClickListener(this)
        binding.buttonVolumeUp.setOnClickListener(this)
        binding.buttonVolumeDown.setOnClickListener(this)
        binding.buttonMute.setOnClickListener(this)
        binding.buttonUnMute.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.buttonSetIpAndPSK.id -> {
                if (binding.edtIPAddress.text?.trim().toString().isNotEmpty()
                    && binding.edtPSK.text?.trim().toString().isNotEmpty()
                ) {
                    viewModel.setIPAddress(binding.edtIPAddress.text?.trim().toString())
                    viewModel.setPreShredKey(binding.edtPSK.text?.trim().toString())
                } else {
                    Toast.makeText(this, "Please input your IP and PSK", Toast.LENGTH_SHORT).show()
                }
            }

            binding.buttonPowerOn.id -> {
                viewModel.setPowerStatus(true)
            }

            binding.buttonPowerOff.id -> {
                viewModel.setPowerStatus(false)
            }

            binding.buttonVolumeUp.id -> {
                viewModel.setAudioVolume("+1")
            }

            binding.buttonVolumeDown.id -> {
                viewModel.setAudioVolume("-1")
            }

            binding.buttonMute.id -> {
                viewModel.setAudioMute(true)
            }

            binding.buttonUnMute.id -> {
                viewModel.setAudioMute(false)
            }
        }
    }
}