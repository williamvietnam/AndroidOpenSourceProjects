package com.android.apps.appFakeCall.features.contactInformation

import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.R
import com.android.apps.appFakeCall.data.entities.ContactEntity
import com.android.base.BaseFragment
import com.android.common.Constants
import com.android.common.Preferences
import com.android.container.MainActivity
import com.android.databinding.FragmentContactInformationBinding

class ContactInformationFragment : BaseFragment<
        FragmentContactInformationBinding,
        ContactInformationViewModel>() {
    private var contact: ContactEntity? = null
    private var isRecord: Boolean = false
    private var timeCall: Int = 0

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContactInformationBinding {
        return FragmentContactInformationBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): ContactInformationViewModel {
        return ViewModelProvider(this)[ContactInformationViewModel::class.java]
    }

    override fun initializeViews() {
        Preferences.instance.set(Constants.IS_FAKE_CALL_RECORD, false)
        contact = this.arguments?.getSerializable(Constants.FAKE_CALL_INFORMATION) as ContactEntity
        binding.view.visibility = View.GONE
        if (contact != null) {
            binding.textName.text = contact!!.contactName
            binding.textNumber.text = contact!!.contactNumber
            if (contact != null) {
                if (contact!!.isDataBase) {
                    val uriDatabase = Uri.parse("${contact!!.contactIcon}")
                    binding.avatar.setImageURI(uriDatabase)
                } else {
                    val drawableAvt = viewModel.getImageFromAsset(
                        fileName = "app_fake_call/images/${contact!!.contactIcon}.jpg",
                        requireContext()
                    )
                    binding.avatar.setImageDrawable(drawableAvt)
                }
            }
        }
    }

    override fun initializeEvents() {
        binding.buttonClose.setOnClickListener {
           findNavController().popBackStack()
        }

        binding.seekArc.setOnSeekArcChangeListener(object : SeekArc.OnSeekArcChangeListener {
            override fun onProgressChanged(seekArc: SeekArc?, progress: Int, fromUser: Boolean) {
                timeCall = (10 * progress * 1000)
                // convert second to mm:ss
                val min: Int = timeCall / (60 * 1000)
                val sec: Int = (timeCall / 1000) % 60
                binding.textTime.text = String.format("%02d:%02d", min, sec)
            }

            override fun onStartTrackingTouch(seekArc: SeekArc?) {
                //TODO: implement later
            }

            override fun onStopTrackingTouch(seekArc: SeekArc?) {
                //TODO: implement later
            }
        })

        binding.buttonAudioCall.setOnClickListener {
            // check isRecord
            if (isRecord) {
                if ((requireActivity() as MainActivity).hasPermissions) {
                    (requireActivity() as MainActivity).startRecordingScreen()
                }
            }

            // check time wait call receive and to navigate WaitTheFakeCall screen
            if (this.timeCall != 0) {
                binding.view.visibility = View.VISIBLE

                val decorView: View = requireActivity().window.decorView
                val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
                decorView.systemUiVisibility = uiOptions
                val actionBar: android.app.ActionBar? = requireActivity().actionBar
                actionBar?.hide()

                object : CountDownTimer(this.timeCall.toLong(), 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                    }

                    override fun onFinish() {
                        Preferences.instance.set(Constants.CALL_MODE, false)
                        val bundle = Bundle()
                        bundle.putSerializable(Constants.FAKE_CALL_DATA, contact)
                        findNavController().navigate(
                            resId = R.id.action_contactInfo_to_incomingCall,
                            args = bundle
                        )
                    }
                }.start()
            } else {
                binding.view.visibility = View.GONE
                Preferences.instance.set(Constants.CALL_MODE, false)
                val bundle = Bundle()
                bundle.putSerializable(Constants.FAKE_CALL_DATA, contact)
                findNavController().navigate(
                    resId = R.id.action_contactInfo_to_incomingCall,
                    args = bundle
                )
            }
        }

        binding.buttonRecord.setOnClickListener {
            if (!isRecord) {
                this.isRecord = true
                Preferences.instance.set(Constants.IS_FAKE_CALL_RECORD, true)
                binding.imageRecord.background =
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_record_active)
            } else {
                binding.imageRecord.background =
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_record_inactive)
            }
        }

        binding.buttonVideoCall.setOnClickListener {

            // check isRecord
            if (isRecord) {
                if ((requireActivity() as MainActivity).hasPermissions) {
                    (requireActivity() as MainActivity).startRecordingScreen()
                }
            }

            // check time wait call receive and to navigate WaitTheFakeCall screen
            if (this.timeCall != 0) {
                binding.view.visibility = View.VISIBLE

                val decorView: View = requireActivity().window.decorView
                val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
                decorView.systemUiVisibility = uiOptions
                val actionBar: android.app.ActionBar? = requireActivity().actionBar
                actionBar?.hide()

                object : CountDownTimer(this.timeCall.toLong(), 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                    }

                    override fun onFinish() {
                        Preferences.instance.set(Constants.CALL_MODE, true)
                        val bundle = Bundle()
                        bundle.putSerializable(Constants.FAKE_CALL_DATA, contact)
                        findNavController().navigate(
                            R.id.action_contactInfo_to_incomingCall,
                            bundle
                        )
                    }
                }.start()
            } else {
                binding.view.visibility = View.GONE
                Preferences.instance.set(Constants.CALL_MODE, true)
                val bundle = Bundle()
                bundle.putSerializable(Constants.FAKE_CALL_DATA, contact)
                findNavController().navigate(R.id.action_contactInfo_to_incomingCall, bundle)
            }
        }
    }
}