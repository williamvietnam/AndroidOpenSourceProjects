package com.remote.brands.sony.container

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.remote.brands.sony.api.SonyIRCC
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

        binding.button1.setOnClickListener(this)
        binding.button2.setOnClickListener(this)
        binding.button3.setOnClickListener(this)
        binding.button4.setOnClickListener(this)
        binding.button5.setOnClickListener(this)
        binding.button6.setOnClickListener(this)
        binding.button7.setOnClickListener(this)
        binding.button8.setOnClickListener(this)
        binding.button9.setOnClickListener(this)
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

            binding.button1.id -> {
                viewModel.setRemoteController(SonyIRCC.Num1)
            }

            binding.button2.id -> {
                viewModel.setRemoteController(SonyIRCC.Num2)
            }

            binding.button3.id -> {
                viewModel.setRemoteController(SonyIRCC.Num3)
            }

            binding.button4.id -> {
                viewModel.setRemoteController(SonyIRCC.Num4)
            }

            binding.button5.id -> {
                viewModel.setRemoteController(SonyIRCC.Num5)
            }

            binding.button6.id -> {
                viewModel.setRemoteController(SonyIRCC.Num6)
            }

            binding.button7.id -> {
                viewModel.setRemoteController(SonyIRCC.Num7)
            }

            binding.button8.id -> {
                viewModel.setRemoteController(SonyIRCC.Num8)
            }

            binding.button9.id -> {
                viewModel.setRemoteController(SonyIRCC.Num9)
            }
        }
    }
}