package com.android.apps.appFakeCall.features.afterTheCall

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.core.base.BaseFragment
import com.android.core.common.Constants
import com.android.core.common.Preferences
import com.android.container.MainActivity
import com.android.databinding.FragmentAfterTheCallBinding

class AfterTheCallFragment : BaseFragment<FragmentAfterTheCallBinding, AfterTheCallViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAfterTheCallBinding {
        return FragmentAfterTheCallBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): AfterTheCallViewModel {
        return ViewModelProvider(this)[AfterTheCallViewModel::class.java]
    }

    override fun initializeViews() {
        // play video in viewView
        binding.videoView.visibility = View.VISIBLE
        binding.videoView.setVideoURI((requireActivity() as MainActivity).videoUri)
        binding.videoView.requestFocus()
        binding.videoView.start()
    }

    override fun initializeEvents() {
//        binding.topView.buttonBack.setOnClickListener {
//            if (Preferences.instance.get(Constants.IS_FAKE_CALL_RECORD, false) as Boolean) {
//                Preferences.instance.set(Constants.IS_FAKE_CALL_RECORD, false)
//            }
//            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commitNow()
//        }

        binding.buttonShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "video/*"

            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
            intent.putExtra(Intent.EXTRA_TITLE, "Title")
            intent.putExtra(Intent.EXTRA_STREAM, (requireActivity() as MainActivity).videoUri)

            startActivity(Intent.createChooser(intent, "Video"))
        }
    }
}