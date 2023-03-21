package com.android.tutorials.recyclerview.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.databinding.FragmentRecyclerviewDemoBinding

class RecyclerviewDemoFragment : Fragment() {

    private var _binding: FragmentRecyclerviewDemoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecyclerviewDemoBinding.inflate(inflater, container, false)
        return binding.root
    }
}