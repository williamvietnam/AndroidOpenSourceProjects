package com.remote.brands.sony.features

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.remote.brands.sony.api.SonyApiEndpoints
import com.remote.brands.sony.api.SonyIRCC
import com.remote.commons.base.BaseFragment
import com.remote.databinding.FragmentSonyTvRemoteControlBinding

class SonyTVRemoteControlFragment :
    BaseFragment<FragmentSonyTvRemoteControlBinding, SonyTVRemoteControlViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSonyTvRemoteControlBinding {
        return FragmentSonyTvRemoteControlBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): SonyTVRemoteControlViewModel {
        return ViewModelProvider(this)[SonyTVRemoteControlViewModel::class.java]
    }

    override fun initializeViews() {

    }

    override fun initializeEvents() {
        binding.buttonSendIPAddress.setOnClickListener {
            if (binding.edtInputIPAddress.text.trim().toString().isNotEmpty()) {
                SonyApiEndpoints.initializeSonyBaseUrl(
                    apiAddress = binding.edtInputIPAddress.text.trim().toString()
                )
            } else {
                Toast.makeText(requireContext(), "Input IP Address", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonSupportedApiInfo.setOnClickListener {
            viewModel.getSupportedServicesInfo().observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it.result[0].service, Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonMute.setOnClickListener {
            viewModel.setAudioMute(true)
        }

        binding.buttonUnMute.setOnClickListener {
            viewModel.setAudioMute(false)
        }

        binding.buttonMinus.setOnClickListener {
            viewModel.setAudioVolume("-1")
        }

        binding.buttonPlus.setOnClickListener {
            viewModel.setAudioVolume("+1")
        }

        binding.buttonPowerOn.setOnClickListener {
            viewModel.setPowerStatus(true)
        }

        binding.buttonPowerOff.setOnClickListener {
            viewModel.setPowerStatus(false)
        }

        binding.buttonUp.setOnClickListener {
            viewModel.setRemoteController(SonyIRCC.Up)
        }

        binding.buttonDown.setOnClickListener {
            viewModel.setRemoteController(SonyIRCC.Down)
        }

        binding.buttonLeft.setOnClickListener {
            viewModel.setRemoteController(SonyIRCC.Left)
        }

        binding.buttonRight.setOnClickListener {
            viewModel.setRemoteController(SonyIRCC.Right)
        }
    }
}