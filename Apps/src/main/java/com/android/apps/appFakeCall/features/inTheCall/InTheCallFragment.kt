package com.android.apps.appFakeCall.features.inTheCall

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.R
import com.android.apps.appFakeCall.data.entities.ContactEntity
import com.android.apps.appFakeCall.utils.CountUpTimer
import com.android.core.base.BaseFragment
import com.android.core.common.Constants
import com.android.core.common.Preferences
import com.android.container.MainActivity
import com.android.databinding.FragmentInTheCallBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class InTheCallFragment : BaseFragment<FragmentInTheCallBinding, InTheCallViewModel>() {

    private var contact: ContactEntity? = null
    private var isVideoCall: Boolean = false

    //camera
    private lateinit var cameraExecutor: ExecutorService

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInTheCallBinding {
        cameraExecutor = Executors.newSingleThreadExecutor()
        return FragmentInTheCallBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): InTheCallViewModel {
        return ViewModelProvider(this)[InTheCallViewModel::class.java]
    }

    override fun initializeViews() {
        isVideoCall = Preferences.instance.get(Constants.CALL_MODE, false) as Boolean
        contact = requireArguments().getSerializable(Constants.FAKE_CALL_DATA) as ContactEntity

        // check show video call or audio call
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
        startCamera()

        // count up timer | default: 1000seconds, when finish = click button turn off the call
        object : CountUpTimer(1000000) {
            override fun onTick(totalSecond: Int) {
                val min = (totalSecond % 3600) / 60
                val second = totalSecond % 60
                val time = String.format("%02d:%02d", min, second)
                binding.textTimeCall.text = time
                binding.textTimeVideoCall.text = time
            }

            override fun onFinish() {
                super.onFinish()
                if (Preferences.instance.get(Constants.IS_FAKE_CALL_RECORD, false) as Boolean
                ) {
                    Preferences.instance.set(Constants.IS_FAKE_CALL_RECORD, false)
                }
                activity?.supportFragmentManager?.beginTransaction()
                    ?.remove(this@InTheCallFragment)?.commitNow()
            }
        }.start()

        // show resources to views
        if (contact != null) {
            //setup show video mp4
            if (contact!!.isDataBase) {
                val uriDatabase = Uri.parse("${contact!!.contactVideo}")
                binding.videoView.setVideoURI(uriDatabase)
            } else {
                val uriAsset = viewModel.getRawUriFile(
                    "${contact!!.contactVideo}",
                    requireContext()
                )
                binding.videoView.setVideoURI(uriAsset)
            }
            binding.videoView.setOnPreparedListener(object : MediaPlayer.OnPreparedListener {
                override fun onPrepared(mediaPlayer: MediaPlayer?) {
                    mediaPlayer?.isLooping = true
                }
            })
            binding.videoView.requestFocus()
            Handler(requireContext().mainLooper).postDelayed(object : Runnable {
                override fun run() {
                    binding.videoView.start()
                }
            }, 1500)

            // setup show text title name
            binding.textName.text = contact!!.contactName

            // setup show avt and background
            if (contact!!.isDataBase) {
                val uriDatabase = Uri.parse("${contact!!.contactIcon}")
                binding.imvAvatar.setImageURI(uriDatabase)
                binding.imageBackground.setImageURI(uriDatabase)
            } else {
                val drawableAvt = viewModel.getImageFromAsset(
                    fileName = "app_fake_call/images/${contact!!.contactIcon}.jpg",
                    requireContext()
                )
                binding.imvAvatar.setImageDrawable(drawableAvt)
                binding.imageBackground.setImageDrawable(drawableAvt)
            }
        }

        if (Preferences.instance.get(Constants.CALL_MODE, false) as Boolean) {
            this.initializeVideoCallViews()
        } else {
            this.initializeAudioCallViews()
        }
    }

    override fun initializeEvents() {
        //actions after answer
        binding.buttonCallMode.setOnClickListener {
            if (!isVideoCall) {
                initializeVideoCallViews()
                if (allPermissionsGranted()) {
                    isVideoCall = true
                    Preferences.instance.set(Constants.CALL_MODE, true)
                    startCamera()
                } else {
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        REQUIRED_PERMISSIONS,
                        REQUEST_CODE_PERMISSIONS
                    )
                }
            } else {
                initializeAudioCallViews()
                isVideoCall = false
                Preferences.instance.set(Constants.CALL_MODE, false)
            }
        }

        binding.buttonTurnOffCall.setOnClickListener {
            if (Preferences.instance.get(Constants.IS_FAKE_CALL_RECORD, false) as Boolean) {
                if ((requireActivity() as MainActivity).hasPermissions) {
                    if ((requireActivity() as MainActivity).hbRecorder!!.isBusyRecording) {
                        (requireActivity() as MainActivity).hbRecorder?.stopScreenRecording()
                    }
                }
                findNavController().navigate(R.id.action_inTheCall_to_afterTheCall)
            } else {
                findNavController().popBackStack()
            }
        }

        binding.buttonMic.setOnClickListener {
            Toast.makeText(requireContext(), "developing", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initializeAudioCallViews() {
        binding.rlAudiCall.visibility = View.VISIBLE
        binding.flVideoCall.visibility = View.INVISIBLE
        cameraExecutor.shutdown()
        if (contact != null) {
            if (contact!!.isDataBase) {
                val uriDatabase = Uri.parse("${contact!!.contactIcon}")
                binding.imvAvatar.setImageURI(uriDatabase)
                binding.imageBackground.setImageURI(uriDatabase)
            } else {
                val drawableAvt = viewModel.getImageFromAsset(
                    fileName = "app_fake_call/images/${contact!!.contactIcon}.jpg",
                    requireContext()
                )
                binding.imvAvatar.setImageDrawable(drawableAvt)
                binding.imageBackground.setImageDrawable(drawableAvt)
            }
        }
    }

    private fun initializeVideoCallViews() {
        binding.rlAudiCall.visibility = View.INVISIBLE
        binding.flVideoCall.visibility = View.VISIBLE
    }

    //------------------------------------Video Call-----------------------------------------------
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener(Runnable {
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.previewView.surfaceProvider)
                }

            val imageCapture = ImageCapture.Builder().build()

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    // checks the camera permission
    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            // If all permissions granted , then start Camera
            if (allPermissionsGranted()) {
                startCamera()
            }
        }
    }

    companion object {
        const val REQUEST_CODE_PERMISSIONS = 20
        val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cameraExecutor.shutdown()
    }
}