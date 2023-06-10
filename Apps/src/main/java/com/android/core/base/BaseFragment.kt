package com.android.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment(), BaseView {

    private var _binding: VB? = null
    val binding: VB
        get() = _binding ?: throw IllegalStateException(
            "Cannot access view after view destroyed or before view creation"
        )

    lateinit var viewModel: VM
    lateinit var sharedViewModel: SharedViewModel

    abstract fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    abstract fun createViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = createViewModel()
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = createViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()
        initializeEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}