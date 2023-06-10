package com.android.apps.appPrankSound.sounds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.R
import com.android.apps.appPrankSound.models.Sound
import com.android.apps.appPrankSound.models.SoundCategory
import com.android.core.base.BaseFragment
import com.android.core.common.Constants
import com.android.databinding.FragmentSoundsBinding

class SoundsFragment : BaseFragment<FragmentSoundsBinding, SoundsViewModel>(),
    SoundsAdapter.ISoundsCallBack {

    private lateinit var adapter: SoundsAdapter

    override fun createViewModel(): SoundsViewModel {
        return ViewModelProvider(this)[SoundsViewModel::class.java]
    }

    override fun createViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentSoundsBinding {
        viewModel.setSoundCategory(arguments?.getSerializable(Constants.PRANK_SOUND_DATA) as SoundCategory)
        return FragmentSoundsBinding.inflate(inflater, container, false)
    }

    override fun initializeViews() {
        viewModel.data.observe(viewLifecycleOwner) {
            binding.textScreenTitle.text = it.nameCategory
            adapter = SoundsAdapter(it.listSound, this)
            binding.recyclerview.adapter = adapter
        }
    }

    override fun initializeEvents() {
        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onSoundClick(sound: Sound) {
        val argument = Bundle()
        argument.putSerializable(Constants.DETAIL_PRANK_SOUND_DATA, sound)
        findNavController().navigate(R.id.action_sounds_to_sound, argument)
    }
}