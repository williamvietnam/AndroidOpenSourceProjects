package com.android.vncalling.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.android.vncalling.ui.features.container.MainActivity
import com.android.vncalling.ui.features.container.MainView

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment(), BaseView {

    lateinit var viewModel: VM
    private var _binding: VB? = null
    val binding get() = _binding!!

    private lateinit var mainView: MainView

    abstract fun createViewModel(): VM
    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
        mainView = MainActivity.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    override fun getMainInstance(): MainView {
        return this.mainView
    }

    override fun hideBottomNavigationView(isHidden: Boolean) {
        this.mainView.hideBottomNavigationView(isHidden)
    }

    override fun showToast(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showToast(@StringRes message: Int?) {
        if (message != null) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }
}