package com.android.vncalling.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {

    lateinit var viewModel: VM
    lateinit var binding: VB

    abstract fun createViewModel(): VM
    abstract fun getViewBinding(): VB
    abstract fun initialize()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()

        this.binding = getViewBinding()
        setContentView(this.binding.root)

        initialize()
    }
}