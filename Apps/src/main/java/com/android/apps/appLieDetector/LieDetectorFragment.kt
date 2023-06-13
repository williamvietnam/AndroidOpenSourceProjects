package com.android.apps.appLieDetector

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.*
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.R
import com.android.apps.appLieDetector.questionpack.LieDetectorQuestionsPacksFragment
import com.android.core.base.BaseFragment
import com.android.core.common.Constants
import com.android.core.common.PlayerManager
import com.android.core.common.Preferences
import com.android.databinding.FragmentLieDetectorBinding
import com.android.databinding.LieDetectorResultDialogBinding
import com.prank.sounds.fake.videocall.screens.liedetector.models.QuestionPack

class LieDetectorFragment : BaseFragment<FragmentLieDetectorBinding, LieDetectorViewModel>() {
    companion object {
        const val NOT_HAVE_ANSWER = 0
        const val YES = 1
        const val NO = 2

        const val CONDITION_QUESTION_ANSWER =
            "TYPE YOUR QUESTION ANSWER AND FINGERPRINT SCAN TO DETECTOR"
        const val CONDITION_QUESTION = "TYPE YOUR QUESTION"
        const val CONDITION_ANSWER = "CHOOSE YOUR ANSWER"
    }

    private var questionPosition = 0
    private var answerYesNo = NOT_HAVE_ANSWER // 0:not chose answer | 1: yes | 2: no

    // false: ask your question | true: default question pack
    private var isDefaultQuestionPack = false

    private var vibration: Vibrator? = null

    private var countDownTimer: CountDownTimer? = null

    private lateinit var handler: Handler

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLieDetectorBinding {
        return FragmentLieDetectorBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): LieDetectorViewModel {
        return ViewModelProvider(this)[LieDetectorViewModel::class.java]
    }

    override fun initializeViews() {
        binding.imvButtonLieDetector.isEnabled = true
        vibration = activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        handler = Handler(requireContext().mainLooper)

        sharedViewModel.questionPack.observe(viewLifecycleOwner) {
            if (it.questionPack == QuestionPack.ASK_YOUR_QUESTION) {
                binding.edtQuestion.visibility = View.VISIBLE
                binding.tvQuestion.visibility = View.GONE
                isDefaultQuestionPack = false
            } else if ((it.questionPack == QuestionPack.COUPLE) || (it.questionPack == QuestionPack.FRIENDS)
                || (it.questionPack == QuestionPack.KIDS) || (it.questionPack == QuestionPack.WORK)
                || (it.questionPack == QuestionPack.ADULTS) || (it.questionPack == QuestionPack.PROBE)
            ) {
                questionPosition = 0
                binding.edtQuestion.visibility = View.GONE
                binding.tvQuestion.visibility = View.VISIBLE
                viewModel.setData(it)
                binding.tvQuestion.text =
                    viewModel.getData()?.questions?.get(questionPosition)?.question
                isDefaultQuestionPack = true
            } else if (Preferences.instance.get(
                    Constants.LIE_DETECTOR_OPEN_SCREEN,
                    true
                ) as Boolean
            ) {
                binding.edtQuestion.visibility = View.VISIBLE
                binding.tvQuestion.visibility = View.GONE
                isDefaultQuestionPack = false
                Preferences.instance.set(Constants.LIE_DETECTOR_OPEN_SCREEN, false)
            } else {
                binding.edtQuestion.visibility = View.VISIBLE
                binding.tvQuestion.visibility = View.GONE
                isDefaultQuestionPack = false
            }
        }
    }

    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    override fun initializeEvents() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            })

        binding.buttonBack.setOnClickListener {
            PlayerManager.shared.stop()
            handler.removeCallbacksAndMessages(null)
            findNavController().popBackStack()
        }

        binding.buttonNo.setOnClickListener {
            binding.buttonNo.background = AppCompatResources.getDrawable(
                requireContext(), R.drawable.background_round_stroke_red
            )
            binding.buttonNo.setTextColor(requireContext().getColor(R.color.color_FB3131))
            binding.buttonYes.background = AppCompatResources.getDrawable(
                requireContext(), R.drawable.background_round_stroke_white
            )
            binding.buttonYes.setTextColor(requireContext().getColor(R.color.white))
            binding.tvAnswer.text = "Your answer is No"
            binding.tvAnswer.visibility = View.VISIBLE
            this.answerYesNo = NO
            if (binding.tvCondition.text == CONDITION_ANSWER) {
                binding.tvCondition.text = ""
            } else if (binding.tvCondition.text == CONDITION_QUESTION_ANSWER) {
                binding.tvCondition.text = CONDITION_QUESTION
            }
        }

        binding.buttonYes.setOnClickListener {
            binding.buttonNo.background = AppCompatResources.getDrawable(
                requireContext(), R.drawable.background_round_stroke_white
            )
            binding.buttonNo.setTextColor(requireContext().getColor(R.color.white))
            binding.buttonYes.background = AppCompatResources.getDrawable(
                requireContext(), R.drawable.background_round_stroke_green
            )
            binding.buttonYes.setTextColor(requireContext().getColor(R.color.color_05F73F))
            binding.tvAnswer.text = "Your answer is Yes"
            binding.tvAnswer.visibility = View.VISIBLE
            this.answerYesNo = YES
            if (binding.tvCondition.text == CONDITION_ANSWER) {
                binding.tvCondition.text = ""
            } else if (binding.tvCondition.text == CONDITION_QUESTION_ANSWER) {
                binding.tvCondition.text = CONDITION_QUESTION
            }
        }

        binding.imvQuestionPack.setOnClickListener {
            showQuestionsPacks()
        }

        binding.imvButtonLieDetector.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        if (!isInputQuestion() && (answerYesNo == NOT_HAVE_ANSWER)) {
                            binding.tvCondition.text = CONDITION_QUESTION_ANSWER
                            return false
                        } else if (!isInputQuestion()) {
                            binding.tvCondition.text = CONDITION_QUESTION
                            return false
                        } else if (answerYesNo == NOT_HAVE_ANSWER) {
                            binding.tvCondition.text = CONDITION_ANSWER
                            return false
                        } else {
                            binding.imvButtonLieDetector.setImageDrawable(
                                AppCompatResources.getDrawable(
                                    requireContext(), R.drawable.ic_ai_chat_button_circle_red
                                )
                            )
                            if (Build.VERSION.SDK_INT >= 26) {
                                vibration?.vibrate(
                                    VibrationEffect.createOneShot(
                                        4000, VibrationEffect.DEFAULT_AMPLITUDE
                                    )
                                )
                            } else {
                                vibration?.vibrate(4000)
                            }
                            PlayerManager.shared.play(
                                "app_lie_detector/mp3/scan.mp3",
                                true,
                                requireContext()
                            )

                            binding.tvAnswer.visibility = View.GONE
                            binding.tvCondition.visibility = View.GONE
                            binding.animationWave.visibility = View.VISIBLE
                            binding.animationWave.playAnimation()

                            countDownTimer = object : CountDownTimer(4000, 1000) {
                                override fun onTick(millisUntilFinished: Long) {
                                    val second = (millisUntilFinished / 1000).toInt()
                                    if (second % 2 == 0) {
                                        binding.imvLightLeft.setImageDrawable(
                                            AppCompatResources.getDrawable(
                                                requireContext(),
                                                R.drawable.ic_lie_detector_circle_green
                                            )
                                        )
                                        binding.imvLightRight.setImageDrawable(
                                            AppCompatResources.getDrawable(
                                                requireContext(),
                                                R.drawable.ic_lie_detector_circle_red
                                            )
                                        )
                                    } else {
                                        binding.imvLightLeft.setImageDrawable(
                                            AppCompatResources.getDrawable(
                                                requireContext(),
                                                R.drawable.ic_lie_detector_circle_red
                                            )
                                        )
                                        binding.imvLightRight.setImageDrawable(
                                            AppCompatResources.getDrawable(
                                                requireContext(),
                                                R.drawable.ic_lie_detector_circle_green
                                            )
                                        )
                                    }
                                }

                                override fun onFinish() {
                                    binding.imvButtonLieDetector.isEnabled = false
                                    PlayerManager.shared.stop()
                                    vibration?.cancel()
                                    binding.animationWave.cancelAnimation()
                                    binding.animationWave.visibility = View.GONE
                                    binding.tvCondition.visibility = View.VISIBLE
                                    binding.imvButtonLieDetector.setImageDrawable(
                                        AppCompatResources.getDrawable(
                                            requireContext(),
                                            R.drawable.ic_ai_chat_button_circle_green
                                        )
                                    )
                                    binding.tvCondition.text = "finger analyzing"
                                    handler.postDelayed({
                                        binding.tvCondition.text = "analyzing"
                                    }, 2000)
                                    handler.postDelayed({
                                        binding.tvCondition.text = ""
                                        showResult(viewModel.getRandomBoolean())
                                    }, 3500)
                                }
                            }
                            countDownTimer?.start()
                        }
                    }

                    MotionEvent.ACTION_UP -> {
                        PlayerManager.shared.stop()
                        vibration?.cancel()
                        countDownTimer?.cancel()
                        binding.animationWave.cancelAnimation()
                        binding.animationWave.visibility = View.GONE
                        binding.imvButtonLieDetector.setImageDrawable(
                            AppCompatResources.getDrawable(
                                requireContext(), R.drawable.ic_ai_chat_button_circle_green
                            )
                        )
                        binding.tvCondition.text = "WAIT 4 SECONDS TO SCAN..."
                        binding.tvCondition.visibility = View.VISIBLE
                    }

                    MotionEvent.ACTION_CANCEL -> {
                        PlayerManager.shared.stop()
                        vibration?.cancel()
                        countDownTimer?.cancel()
                        binding.animationWave.cancelAnimation()
                        binding.animationWave.visibility = View.GONE
                        binding.imvButtonLieDetector.setImageDrawable(
                            AppCompatResources.getDrawable(
                                requireContext(), R.drawable.ic_ai_chat_button_circle_green
                            )
                        )
                        binding.tvCondition.text = "WAIT 4 SECONDS TO SCAN..."
                        binding.tvCondition.visibility = View.VISIBLE
                    }
                }
                return true
            }
        })
    }

    private fun showQuestionsPacks() {
        val fragment = LieDetectorQuestionsPacksFragment()
        activity?.supportFragmentManager?.commit {
            add(android.R.id.content, fragment)
            addToBackStack(null)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showResult(isTruth: Boolean) {
        binding.imvButtonLieDetector.isEnabled = true
        val builder = AlertDialog.Builder(requireContext())
        val inflater = LayoutInflater.from(requireContext())
        val dialogBinding = LieDetectorResultDialogBinding.inflate(inflater)
        builder.setView(dialogBinding.root)
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        if (isTruth) {
            PlayerManager.shared.play(
                "app_lie_detector/mp3/correctanswer.mp3",
                false,
                requireContext()
            )
            dialogBinding.tvResult.text = "You are Truth"
            dialogBinding.tvResult.setTextColor(requireContext().getColor(R.color.color_05F73F))
        } else {
            PlayerManager.shared.play(
                "app_lie_detector/mp3/wronganswer.mp3",
                false,
                requireContext()
            )
            dialogBinding.tvResult.text = "You are lie"
            dialogBinding.tvResult.setTextColor(requireContext().getColor(R.color.color_FB3131))
        }

        dialogBinding.buttonPlayAgain.setOnClickListener {
            if (isDefaultQuestionPack) {
                if (questionPosition < viewModel.getData()?.questions?.size!!) {
                    questionPosition += 1
                    binding.tvQuestion.text =
                        viewModel.getData()?.questions?.get(questionPosition)?.question
                } else {
                    questionPosition = 0
                }
            } else {
                binding.edtQuestion.setText("")
            }
            binding.tvAnswer.text = ""
            binding.tvAnswer.visibility = View.GONE
            answerYesNo = NOT_HAVE_ANSWER
            binding.buttonNo.background = AppCompatResources.getDrawable(
                requireContext(), R.drawable.background_round_stroke_white
            )
            binding.buttonNo.setTextColor(requireContext().getColor(R.color.white))
            binding.buttonYes.background = AppCompatResources.getDrawable(
                requireContext(), R.drawable.background_round_stroke_white
            )
            binding.buttonYes.setTextColor(requireContext().getColor(R.color.white))
            PlayerManager.shared.stop()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun isInputQuestion(): Boolean {
        return if (isDefaultQuestionPack) {
            binding.tvQuestion.text.isNotEmpty()
        } else {
            binding.edtQuestion.text.toString().trim().isNotEmpty()
        }
    }

}