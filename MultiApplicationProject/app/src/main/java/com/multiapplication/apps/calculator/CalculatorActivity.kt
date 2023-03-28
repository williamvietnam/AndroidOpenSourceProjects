package com.multiapplication.apps.calculator

import androidx.lifecycle.ViewModelProvider
import com.multiapplication.base.BaseActivity
import com.multiapplication.databinding.ActivityCalculatorBinding

class CalculatorActivity : BaseActivity<ActivityCalculatorBinding, CalculatorViewModel>() {
    override fun createViewBinding(): ActivityCalculatorBinding {
        return ActivityCalculatorBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): CalculatorViewModel {
        return ViewModelProvider(this)[CalculatorViewModel::class.java]
    }

    override fun initializeView() {

    }
}