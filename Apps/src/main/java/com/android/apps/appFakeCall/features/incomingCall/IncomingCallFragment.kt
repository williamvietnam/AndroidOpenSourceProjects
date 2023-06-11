package com.android.apps.appFakeCall.features.incomingCall

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.R
import com.android.apps.appFakeCall.data.entities.ContactEntity
import com.android.core.base.BaseFragment
import com.android.core.common.Constants
import com.android.core.common.PlayerManager
import com.android.core.common.Preferences
import com.android.databinding.FragmentIncomingCallBinding

class IncomingCallFragment : BaseFragment<FragmentIncomingCallBinding, IncomingCallViewModel>() {

    private var contact: ContactEntity? = null

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentIncomingCallBinding {
        return FragmentIncomingCallBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): IncomingCallViewModel {
        return ViewModelProvider(this)[IncomingCallViewModel::class.java]
    }

    @SuppressLint("SetTextI18n")
    override fun initializeViews() {
        // re-show status bar
        val decorView: View = requireActivity().window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
        decorView.systemUiVisibility = uiOptions
        val actionBar: android.app.ActionBar? = requireActivity().actionBar
        actionBar?.show()

        this.contact = requireArguments().getSerializable(Constants.FAKE_CALL_DATA) as ContactEntity

        PlayerManager.shared.play("app_fake_call/mp3/ringstone.mp3", true, requireContext())
        if (Preferences.instance.get(Constants.CALL_MODE, false) as Boolean) {
            binding.textStatusCall.text = "Video call from messenger...."
            binding.icAnswer.setImageResource(R.drawable.ic_video)
        } else {
            binding.textStatusCall.text = "Audio call from messenger...."
            binding.icAnswer.setImageResource(R.drawable.ic_call)
        }
        if (contact != null) {
            binding.textName.text = contact!!.contactName
            if (contact!!.isDataBase) {
                val uriDatabase = Uri.parse("${contact!!.contactIcon}")
                binding.imvAvatar.setImageURI(uriDatabase)
                binding.imageBackground.setImageURI(uriDatabase)
            } else {
                val drawableAvt = viewModel.getImageFromAsset(
                    fileName = "app_fake_call/images/${contact!!.contactIcon}.jpg",
                    context = requireContext()
                )
                binding.imvAvatar.setImageDrawable(drawableAvt)
                binding.imageBackground.setImageDrawable(drawableAvt)
            }
        }
    }

    override fun initializeEvents() {
        binding.buttonDecline.setOnClickListener {
            PlayerManager.shared.stop()
            if (Preferences.instance.get(Constants.IS_FAKE_CALL_RECORD, false) as Boolean) {
                Preferences.instance.set(Constants.IS_FAKE_CALL_RECORD, false)
            }
            findNavController().popBackStack()
        }

        binding.buttonAnswer.setOnClickListener {
            PlayerManager.shared.stop()
            val bundle = Bundle()
            bundle.putSerializable(Constants.FAKE_CALL_DATA, contact)
            findNavController().navigate(R.id.action_incomingCall_to_inTheCall, bundle)
        }
    }
}