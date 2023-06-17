package com.android.apps.appWAStickers.screens.stickers

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.R
import com.android.apps.appWAStickers.core.AddStickerPackFragment
import com.android.apps.appWAStickers.models.Sticker
import com.android.apps.appWAStickers.models.StickerPack
import com.android.commons.utilities.Constants
import com.android.commons.utilities.Preferences
import com.android.databinding.FragmentWAStickerBinding

class WAStickersFragment : AddStickerPackFragment(),
    WAStickerCategoriesAdapter.IStickerCategoryCallBack,
    WAStickersAdapter.IStickerCallBack {

    private lateinit var binding: FragmentWAStickerBinding
    private lateinit var viewModel: WAStickersViewModel
    private lateinit var categoriesAdapter: WAStickerCategoriesAdapter
    private lateinit var detailAdapter: WAStickersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[WAStickersViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentWAStickerBinding.inflate(inflater, container, false)
        requireActivity().overridePendingTransition(0, 0)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        initializeEvents()
    }

    @SuppressLint("SetTextI18n")
    private fun initializeViews() {
        binding.flButton.visibility = View.VISIBLE

        categoriesAdapter = WAStickerCategoriesAdapter(this)
        detailAdapter = WAStickersAdapter(this)

        binding.recyclerViewCategory.adapter = categoriesAdapter
        binding.recyclerViewDetail.adapter = detailAdapter

        viewModel.getStickersData.observe(viewLifecycleOwner) {
            // load sticker package data list to categories adapter
            categoriesAdapter.loadData(it)
            // for first open screen
            if (it.size > 0) {
                // save package name and status whitelisted for first open screen
                Preferences.instance.set(Constants.STICKER_PACKAGE_NAME, it[0].name)
                Preferences.instance.set(Constants.STICKER_IS_WHITELISTED, it[0].isWhitelisted)
                // load data to detail adapter for first open screen
                detailAdapter.loadData(it[0].stickers as ArrayList<Sticker>)
                // save sticker package data for first open screen
                viewModel.stickerPackData = it[0]
                if (it[0].isWhitelisted) {
                    binding.flButton.visibility = View.INVISIBLE
                } else {
                    binding.flButton.visibility = View.VISIBLE
                }
            }
        }
        viewModel.loadData(this.requireContext())
    }

    private fun initializeEvents() {
        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.flButton.setOnClickListener {
            if (viewModel.stickerPackData != null) {
                val data = viewModel.stickerPackData
                addStickerPackToWhatsApp(data?.identifier.toString(), data?.stickerPackName!!)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onStickerCategoryClicked(item: StickerPack) {
        binding.tvButton.text = "Add to WhatsApp"

        if (item.isWhitelisted) {
            binding.flButton.visibility = View.INVISIBLE
        } else {
            binding.flButton.visibility = View.VISIBLE
        }

        item.stickers?.let {
            detailAdapter.loadData(it as ArrayList<Sticker>)
        }

        // save package name and status whitelisted
        item.name?.let {
            Preferences.instance.set(Constants.STICKER_PACKAGE_NAME, it)
        }
        Preferences.instance.set(Constants.STICKER_IS_WHITELISTED, item.isWhitelisted)

        // save stickers size
        item.stickers?.let {
            Preferences.instance.set(Constants.STICKER_DATA_SIZE, it.size)
        }

        // assign stickerPack current
        viewModel.stickerPackData = item
    }

    override fun onStickerClicked(stickers: ArrayList<Sticker>, position: Int) {
        val data = StickerPack(stickers = stickers)
        val bundle = Bundle()
        bundle.putParcelable(Constants.STICKER_DATA, data)
        bundle.putInt(Constants.STICKER_POSITION, position)
        findNavController().navigate(R.id.action_WAStickers_to_WAStickersDetail, bundle)
    }

    companion object {
        const val EXTRA_STICKER_PACK_LIST_DATA = "sticker_pack_list"

        /**
         * Do not change below values of below 3 lines as this is also used by WhatsApp
         */
        const val EXTRA_STICKER_PACK_ID = "sticker_pack_id"
        const val EXTRA_STICKER_PACK_AUTHORITY = "sticker_pack_authority"
        const val EXTRA_STICKER_PACK_NAME = "sticker_pack_name"

        const val EXTRA_STICKER_PACK_WEBSITE = "sticker_pack_website"
        const val EXTRA_STICKER_PACK_EMAIL = "sticker_pack_email"
        const val EXTRA_STICKER_PACK_PRIVACY_POLICY = "sticker_pack_privacy_policy"
        const val EXTRA_STICKER_PACK_LICENSE_AGREEMENT = "sticker_pack_license_agreement"
        const val EXTRA_STICKER_PACK_TRAY_ICON = "sticker_pack_tray_icon"
        const val EXTRA_SHOW_UP_BUTTON = "show_up_button"
        const val EXTRA_STICKER_PACK_DATA = "sticker_pack"
    }

}