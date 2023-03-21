package com.android.tutorials.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.databinding.FragmentRecyclerviewDocsBinding

class RecyclerviewDocsFragment : Fragment() {
    private var _binding: FragmentRecyclerviewDocsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecyclerviewDocsBinding.inflate(inflater, container, false)
        return this.binding.root
    }
}