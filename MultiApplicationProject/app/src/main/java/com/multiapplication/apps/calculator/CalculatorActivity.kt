package com.multiapplication.apps.calculator

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.multiapplication.base.BaseActivity
import com.multiapplication.databinding.ActivityCalculatorBinding

class CalculatorActivity : BaseActivity<ActivityCalculatorBinding, CalculatorViewModel>() {

    private val dataObserver = object : Observer<String> {
        override fun onChanged(data: String?) {
            if (data != null) {
                binding.data.text = data
                if (data.length in 7..9) {
                    binding.data.textSize = 64f
                } else if (data.length in 10..12) {
                    binding.data.textSize = 48f
                } else if (data.length in 13..23)
                    binding.data.textSize = 32f
            }
        }
    }

    override fun createViewBinding(): ActivityCalculatorBinding {
        return ActivityCalculatorBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): CalculatorViewModel {
        return ViewModelProvider(this)[CalculatorViewModel::class.java]
    }

    override fun initializeView() {
        viewModel.data.observe(this, dataObserver)
        eventListeners()
    }

    private fun eventListeners() {
        this.numberEventListeners()
        this.calculationEventListeners()
        this.controllerEventListeners()
    }

    private fun numberEventListeners() {
        this.binding.button0.setOnClickListener {
            viewModel.temporary = "0"
            viewModel.getDataDisplay()
        }

        this.binding.button1.setOnClickListener {
            viewModel.temporary = "1"
            viewModel.getDataDisplay()
        }

        this.binding.button2.setOnClickListener {
            viewModel.temporary = "2"
            viewModel.getDataDisplay()
        }

        this.binding.button3.setOnClickListener {
            viewModel.temporary = "3"
            viewModel.getDataDisplay()
        }

        this.binding.button4.setOnClickListener {
            viewModel.temporary = "4"
            viewModel.getDataDisplay()
        }

        this.binding.button5.setOnClickListener {
            viewModel.temporary = "5"
            viewModel.getDataDisplay()
        }

        this.binding.button6.setOnClickListener {
            viewModel.temporary = "6"
            viewModel.getDataDisplay()
        }

        this.binding.button7.setOnClickListener {
            viewModel.temporary = "7"
            viewModel.getDataDisplay()
        }

        this.binding.button8.setOnClickListener {
            viewModel.temporary = "8"
            viewModel.getDataDisplay()
        }

        this.binding.button9.setOnClickListener {
            viewModel.temporary = "9"
            viewModel.getDataDisplay()
        }
    }

    private fun calculationEventListeners() {
        this.binding.buttonPlus.setOnClickListener {

        }

        this.binding.buttonMinus.setOnClickListener {

        }

        this.binding.buttonMultiple.setOnClickListener {

        }

        this.binding.buttonDivide.setOnClickListener {

        }

        this.binding.buttonEqual.setOnClickListener {

        }
    }

    private fun controllerEventListeners() {
        this.binding.buttonAC.setOnClickListener {

        }

        this.binding.buttonPercent.setOnClickListener {

        }
    }
}