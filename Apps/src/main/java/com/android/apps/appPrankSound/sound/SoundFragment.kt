package com.android.apps.appPrankSound.sound

import android.annotation.SuppressLint
import android.content.Intent
import android.os.CountDownTimer
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.R
import com.android.apps.appPrankSound.data.models.Sound
import com.android.commons.base.BaseFragment
import com.android.commons.utilities.Constants
import com.android.commons.utilities.PlayerManager
import com.android.databinding.FragmentSoundBinding
import com.android.databinding.TimerPickerDialogBinding
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog

class SoundFragment : BaseFragment<FragmentSoundBinding, SoundViewModel>(),
    FavouriteSoundsAdapter.IFavouriteSoundsCallBack {

    private var timer: Long? = null

    private var isPlaySoundClicked: Boolean = false
    private var isLoopClicked: Boolean = false
    private var isFavouriteClicked: Boolean = false

    // Animation scale
    private lateinit var scaleUp: Animation
    private lateinit var scaleDown: Animation

    private var countDownTimer: CountDownTimer? = null

    private lateinit var favouriteSoundsAdapter: FavouriteSoundsAdapter

    override fun createViewModel(): SoundViewModel {
        return ViewModelProvider(this)[SoundViewModel::class.java]
    }

    override fun createViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentSoundBinding {
        viewModel.setSound(arguments?.getSerializable(Constants.DETAIL_PRANK_SOUND_DATA) as Sound)
        scaleUp = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_up)
        scaleDown = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_down)
        return FragmentSoundBinding.inflate(inflater, container, false)
    }

    override fun initializeViews() {
        viewModel.sound.observe(viewLifecycleOwner) {
            binding.textScreenTitle.text = it.name
            Glide.with(requireContext()).load(
                viewModel.getImageFromAsset(
                    "app_prank_sounds/images/${it.category}/${it.icon}.png", requireContext()
                )
            ).into(binding.imageSoundPlay)
        }

        favouriteSoundsAdapter = FavouriteSoundsAdapter(this)
        viewModel.favouriteSounds.observe(viewLifecycleOwner) {
            favouriteSoundsAdapter.loadData(it)
        }
        binding.recyclerview.adapter = favouriteSoundsAdapter
    }

    override fun initializeEvents() {
        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonPlaySound.setOnLongClickListener {
            if (viewModel.sound.value?.category != null && viewModel.sound.value?.sound != null) {
                PlayerManager.shared.play(
                    fileName = "app_prank_sounds/mp3/${viewModel.sound.value?.category}/${viewModel.sound.value?.sound}.mp3",
                    isLoop = true,
                    context = requireContext()
                )
            }
            binding.buttonPlaySound.startAnimation(scaleDown)
            false
        }

        this.binding.buttonPlaySound.setOnClickListener {
            if (!isPlaySoundClicked) {
                isPlaySoundClicked = true
                if (viewModel.sound.value?.category != null && viewModel.sound.value?.sound != null) {
                    PlayerManager.shared.play(
                        "app_prank_sounds/mp3/${viewModel.sound.value?.category}/${viewModel.sound.value?.sound}.mp3",
                        requireContext()
                    )
                }
                binding.buttonPlaySound.startAnimation(scaleUp)
                PlayerManager.shared.getMediaPlayerInstance()
                    ?.setOnCompletionListener { mediaPlayer ->
                        if (mediaPlayer != null) {
                            isPlaySoundClicked = false
                        }
                    }
            } else {
                isPlaySoundClicked = false
                PlayerManager.shared.stop()
                binding.buttonPlaySound.startAnimation(scaleUp)
            }
        }

        this.binding.buttonLoop.setOnClickListener {
            if (!isLoopClicked) {
                this.isLoopClicked = true
                this.binding.buttonLoop.setColorFilter(
                    requireContext().resources.getColor(
                        R.color.color_aba4ff,
                        requireContext().theme
                    )
                )
                PlayerManager.shared.loop(isLoopClicked)
                PlayerManager.shared.getMediaPlayerInstance()?.setOnCompletionListener {
                    PlayerManager.shared.play(
                        "app_prank_sounds/mp3/${viewModel.sound.value?.category}/${viewModel.sound.value?.sound}.mp3",
                        requireContext()
                    )
                }
            } else {
                this.isLoopClicked = false
                this.binding.buttonLoop.setColorFilter(
                    requireContext().resources.getColor(
                        R.color.color_818181,
                        requireContext().theme
                    )
                )
                Handler(requireContext().mainLooper).postDelayed(
                    {
                        PlayerManager.shared.stop()
                        isPlaySoundClicked = false
                    },
                    1500
                )
                PlayerManager.shared.loop(isLoopClicked)
            }
        }

        this.binding.buttonShare.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "audio/*"
            sharingIntent.putExtra(Intent.EXTRA_MIME_TYPES, "")
            startActivity(Intent.createChooser(sharingIntent, "Audio share"))
        }

        this.binding.buttonHeart.setOnClickListener {
            if (!isFavouriteClicked) {
                this.isFavouriteClicked = true
                this.binding.buttonHeart.setImageResource(R.drawable.ic_heart_active)
            } else {
                this.isFavouriteClicked = false
                this.binding.buttonHeart.setImageResource(R.drawable.ic_heart)
            }
//            viewModel.saveFavouriteSound(isFavouriteClicked, requireContext())
        }

        this.binding.buttonTimer.setOnClickListener {
            isPlaySoundClicked = false
            PlayerManager.shared.stop()
            this.showBottomSheetDialog()
        }
    }

    private fun showBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        val bottomSheetBinding = TimerPickerDialogBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        val seconds = arrayOf(
            "Off",
            "5 seconds",
            "10 seconds",
            "15 seconds",
            "30 seconds",
            "40 seconds",
            "60 seconds",
            "2 minutes",
            "5 minutes"
        )
        bottomSheetBinding.timePicker.minValue = 0
        bottomSheetBinding.timePicker.displayedValues = seconds
        bottomSheetBinding.timePicker.maxValue = seconds.size - 1
        bottomSheetBinding.timePicker.setOnValueChangedListener { picker, _, _ ->
            when (picker?.value) {
                0 -> {
                    timer = 0
                }

                1 -> {
                    timer = 5000
                }

                2 -> {
                    timer = 10000
                }

                3 -> {
                    timer = 15000
                }

                4 -> {
                    timer = 30000
                }

                5 -> {
                    timer = 40000
                }

                6 -> {
                    timer = 60000
                }

                7 -> {
                    timer = 120000
                }

                8 -> {
                    timer = 300000
                }
            }
        }
        bottomSheetDialog.setCancelable(false)
        bottomSheetDialog.show()
        bottomSheetBinding.buttonClose.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        bottomSheetBinding.buttonDone.setOnClickListener {
            countDownTimer?.cancel()
            if (timer == 0L) {
                bottomSheetDialog.dismiss()
            } else {
                binding.textTimer.visibility = View.VISIBLE
                countDownTimer = object : CountDownTimer(timer!!, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        val min: Long = millisUntilFinished / (60 * 1000)
                        val sec: Long = (millisUntilFinished / 1000) % 60
                        binding.textTimer.text = String.format("%02d:%02d", min, sec)
                    }

                    @SuppressLint("SetTextI18n")
                    override fun onFinish() {
                        isPlaySoundClicked = true
                        binding.textTimer.visibility = View.INVISIBLE
                        if (viewModel.sound.value?.category != null && viewModel.sound.value?.sound != null) {
                            PlayerManager.shared.play(
                                "app_prank_sounds/mp3/${viewModel.sound.value?.category}/${viewModel.sound.value?.sound}.mp3",
                                requireContext()
                            )
                        }
                        binding.buttonPlaySound.startAnimation(scaleUp)
                    }
                }
                this.countDownTimer?.start()
                bottomSheetDialog.dismiss()
            }
        }
    }

    override fun onDestroyView() {
        PlayerManager.shared.stop()
        this.countDownTimer?.cancel()
        super.onDestroyView()
    }

    override fun onSoundClick(sound: Sound) {

    }
}
