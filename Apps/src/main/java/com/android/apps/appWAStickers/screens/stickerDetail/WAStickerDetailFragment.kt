package com.android.apps.appWAStickers.screens.stickerDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.android.apps.appWAStickers.core.AddStickerPackFragment
import com.android.apps.appWAStickers.models.StickerPack
import com.android.commons.utilities.Constants
import com.android.commons.utilities.Preferences
import com.android.databinding.FragmentWAStickerDetailBinding

class WAStickerDetailFragment : AddStickerPackFragment() {
    private lateinit var binding: FragmentWAStickerDetailBinding
    private lateinit var viewModel: WAStickerDetailViewModel
    private lateinit var pagerAdapter: WAStickerPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[WAStickerDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentWAStickerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.positionStartWhenScreenIsCalled = arguments?.getInt(
            Constants.STICKER_POSITION
        ) as Int
        val data = arguments?.getParcelable(Constants.STICKER_DATA) as? StickerPack
        if (data != null) {
            viewModel.receiveData(data)
        }
        initializeViews()
        initializeEvents()
    }

    private fun initializeViews() {
        binding.tvStickerPackageName.text = Preferences.instance.get(
            Constants.STICKER_PACKAGE_NAME, ""
        ) as String
        pagerAdapter = WAStickerPagerAdapter()
        viewModel.getStickersData.observe(viewLifecycleOwner) {
            pagerAdapter.loadData(it)
            viewModel.dataSize = it.size
        }
        binding.viewPager.adapter = pagerAdapter

        if (Preferences.instance.get(Constants.STICKER_IS_WHITELISTED, false) as Boolean) {
            binding.flButton.visibility = View.INVISIBLE
        } else {
            binding.flButton.visibility = View.VISIBLE
        }

        // set position display when click sticker from StickerFragment
        binding.viewPager.post {
            binding.viewPager.currentItem = viewModel.positionStartWhenScreenIsCalled
        }
    }

    private fun initializeEvents() {
        binding.buttonClose.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonPrevious.setOnClickListener {
            binding.viewPager.currentItem = binding.viewPager.currentItem - 1
            if (binding.viewPager.currentItem > 0) {
                binding.viewPager.setCurrentItem(binding.viewPager.currentItem, true)
            }
        }

        binding.buttonNext.setOnClickListener {
            binding.viewPager.currentItem = binding.viewPager.currentItem + 1
            if (binding.viewPager.currentItem < (viewModel.dataSize - 1)) {
                binding.viewPager.setCurrentItem(binding.viewPager.currentItem, true)
            }
        }

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.indicator.text = String
                    .format("${binding.viewPager.currentItem + 1}/${viewModel.dataSize}")
                if ((position > 0) && (position < (viewModel.dataSize - 1))) {
                    binding.buttonPrevious.visibility = View.VISIBLE
                    binding.buttonNext.visibility = View.VISIBLE
                } else if (position == 0) {
                    binding.buttonPrevious.visibility = View.GONE
                } else if (position == (viewModel.dataSize - 1)) {
                    binding.buttonNext.visibility = View.GONE
                }
            }
        })

        binding.flButton.setOnClickListener {
            viewModel.getStickerPackData.observe(viewLifecycleOwner) {
                addStickerPackToWhatsApp(it.identifier!!, it.stickerPackName!!)
            }
        }
    }
}