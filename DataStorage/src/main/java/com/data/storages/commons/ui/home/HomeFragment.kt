package com.data.storages.commons.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.data.storages.commons.ui.home.HomeItem.Companion.ITEM_HOME_CAMERA_ID
import com.data.storages.commons.ui.home.HomeItem.Companion.ITEM_HOME_CLOUD_STORAGE_ID
import com.data.storages.commons.ui.home.HomeItem.Companion.ITEM_HOME_EXTERNAL_STORAGE_ID
import com.data.storages.commons.ui.home.HomeItem.Companion.ITEM_HOME_INTERNAL_STORAGE_ID
import com.data.storages.commons.ui.home.HomeItem.Companion.ITEM_HOME_REALTIME_DATABASE_ID
import com.data.storages.commons.ui.home.HomeItem.Companion.ITEM_HOME_ROOM_DATABASE_ID
import com.data.storages.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), HomeAdapter.IHomeCallBack {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HomeAdapter(viewModel.initializeHomeItems(), this)
        binding.recyclerview.adapter = adapter
    }

    override fun onItemClick(item: HomeItem) {
        when (item.id) {
            ITEM_HOME_CAMERA_ID -> {
                Toast.makeText(requireContext(), ITEM_HOME_CAMERA_ID, Toast.LENGTH_SHORT).show()
            }

            ITEM_HOME_INTERNAL_STORAGE_ID -> {
                Toast.makeText(requireContext(), ITEM_HOME_INTERNAL_STORAGE_ID, Toast.LENGTH_SHORT)
                    .show()
            }

            ITEM_HOME_EXTERNAL_STORAGE_ID -> {
                Toast.makeText(requireContext(), ITEM_HOME_EXTERNAL_STORAGE_ID, Toast.LENGTH_SHORT)
                    .show()
            }

            ITEM_HOME_CLOUD_STORAGE_ID -> {
                Toast.makeText(requireContext(), ITEM_HOME_CLOUD_STORAGE_ID, Toast.LENGTH_SHORT)
                    .show()
            }

            ITEM_HOME_REALTIME_DATABASE_ID -> {
                Toast.makeText(requireContext(), ITEM_HOME_REALTIME_DATABASE_ID, Toast.LENGTH_SHORT)
                    .show()
            }

            ITEM_HOME_ROOM_DATABASE_ID -> {
                Toast.makeText(requireContext(), ITEM_HOME_ROOM_DATABASE_ID, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}