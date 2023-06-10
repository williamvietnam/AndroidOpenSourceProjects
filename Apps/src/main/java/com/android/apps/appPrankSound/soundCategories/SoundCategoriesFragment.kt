package com.android.apps.appPrankSound.soundCategories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.R
import com.android.apps.appPrankSound.models.SoundCategory
import com.android.core.base.BaseFragment
import com.android.core.common.Constants
import com.android.databinding.FragmentSoundCateogiesBinding

class SoundCategoriesFragment : BaseFragment<
        FragmentSoundCateogiesBinding, SoundCategoriesViewModel>(),
    SoundCategoriesAdapter.ISoundCategoriesCallBack {

    private lateinit var adapter: SoundCategoriesAdapter

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSoundCateogiesBinding {
        return FragmentSoundCateogiesBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): SoundCategoriesViewModel {
        return ViewModelProvider(this)[SoundCategoriesViewModel::class.java]
    }

    override fun initializeViews() {
        viewModel.data.observe(viewLifecycleOwner) {
            adapter = SoundCategoriesAdapter(it, this)
            binding.recyclerview.adapter = adapter
        }
        viewModel.getDataFromAssets(requireContext())
    }

    override fun initializeEvents() {
        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onSoundCategoryClick(soundCategory: SoundCategory) {
        val argument = Bundle()
        argument.putSerializable(Constants.PRANK_SOUND_DATA, soundCategory)
        findNavController().navigate(R.id.action_soundCategories_to_sounds, argument)
    }
}