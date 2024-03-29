package com.android.container.appsMenu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.R
import com.android.commons.base.BaseFragment
import com.android.databinding.FragmentAppsMenuBinding

class AppsMenuFragment : BaseFragment<FragmentAppsMenuBinding, AppsMenuViewModel>(),
    AppsAdapter.AppsCallBack {

    private lateinit var adapter: AppsAdapter
    override fun createViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentAppsMenuBinding {
        return FragmentAppsMenuBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): AppsMenuViewModel {
        return ViewModelProvider(this)[AppsMenuViewModel::class.java]
    }

    override fun initializeViews() {
        // setup recyclerview adapter apps
        adapter = AppsAdapter(viewModel.createApps(), this)
        binding.recyclerview.adapter = adapter
    }

    override fun initializeEvents() {}

    override fun onAppClicked(app: App) {
        when (app.id) {
            App.APP_FAKE_BANKING_ID -> {
                findNavController().navigate(R.id.action_from_appsMenu_to_fakeBanking)
            }

            App.APP_CAMERA_ID -> {
                findNavController().navigate(R.id.action_from_appsMenu_to_camera)
            }

            App.APP_STEP_COUNTER_ID -> {
                findNavController().navigate(R.id.action_from_appsMenu_to_stepCounter)
            }

            App.APP_SECRET_PHOTOS_ID -> {
                findNavController().navigate(R.id.action_from_appsMenu_to_secretPhotos)
            }

            App.APP_PRANK_SOUND_ID -> {
                findNavController().navigate(R.id.action_from_appsMenu_to_soundCategories)
            }

            App.APP_WEATHER_ID -> {
                findNavController().navigate(R.id.action_from_appsMenu_to_weather)
            }

            App.APP_FAKE_CALL_ID -> {
                findNavController().navigate(R.id.action_from_appsMenu_to_contacts)
            }

            App.APP_ALARM_ID -> {
                findNavController().navigate(R.id.action_from_appsMenu_to_alarm)
            }

            App.APP_LIE_DETECTOR_ID -> {
                findNavController().navigate(R.id.action_from_appsMenu_to_lieDetector)
            }

            App.APP_AI_CHAT_ID -> {
                findNavController().navigate(R.id.action_from_appsMenu_to_aiChat)
            }

            App.APP_WHATSAPP_STICKERS_ID -> {
                findNavController().navigate(R.id.action_from_appsMenu_to_waStickers)
            }

            App.APP_PAINT_ID -> {
                findNavController().navigate(R.id.action_from_appsMenu_to_paint)
            }

            App.APP_IMAGE_FILTERS_ID -> {
                findNavController().navigate(R.id.action_from_appsMenu_to_imageFilters)
            }

            App.APP_WALLPAPER_ID -> {
                findNavController().navigate(R.id.action_from_appsMenu_to_wallpaper)
            }

            App.APP_CHARTS_ID -> {
                findNavController().navigate(R.id.action_from_appsMenu_to_charts)
            }

            App.APP_SHORTS_ID -> {
                findNavController().navigate(R.id.action_from_appsMenu_to_videoShorts)
            }

            App.APP_FAKE_CHAT_ID -> {

            }

            App.APP_GUESS_VOICE_ID -> {

            }
        }
    }
}