package com.android.apps.appFakeCall.features.newContact

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_AUDIO
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VIDEO
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.R
import com.android.base.BaseFragment
import com.android.databinding.FragmentNewContactBinding
import java.io.File

class NewContactFragment : BaseFragment<FragmentNewContactBinding, NewContactViewModel>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNewContactBinding {
        return FragmentNewContactBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): NewContactViewModel {
        return ViewModelProvider(this)[NewContactViewModel::class.java]
    }

    private var encodeImage: String? = null
    private var videoUri: String? = null

    companion object {
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        private val STORAGE_PERMISSIONS_33 = arrayOf(
            READ_MEDIA_IMAGES, READ_MEDIA_AUDIO, READ_MEDIA_VIDEO
        )
        private val STORAGE_PERMISSIONS = arrayOf(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE)
    }

    private fun permissions(): Array<String> {
        val permission: Array<String> = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            STORAGE_PERMISSIONS_33
        } else {
            STORAGE_PERMISSIONS
        }
        return permission
    }

    private val pickImage: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback<ActivityResult> { result ->
            if (result.resultCode == RESULT_OK) {
                if (result.data != null) {
                    val uri: Uri = result.data?.data!!
                    val path = getFilePathFromURI(uri, requireActivity())
                    val file = File(path!!)
                    this.encodeImage = file.absolutePath
                    binding.imvAvatar.setImageURI(Uri.parse(this.encodeImage))
                    binding.imvIconAddAvatar.visibility = View.GONE
                    binding.imvIconAvtNull.visibility = View.GONE
                    statusButtonDone(isValidInformationInput())
                }
            }
        })

    private val pickVideo: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback<ActivityResult> { result ->
            if (result.resultCode == RESULT_OK) {
                if (result.data != null) {
                    val uri: Uri = result.data?.data!!
                    val path = getFilePathFromURI(uri, requireActivity())
                    val file = File(path!!)
                    this.videoUri = file.absolutePath
                    binding.videoView.setVideoURI(Uri.parse("${this.videoUri}"))
                    binding.videoView.requestFocus()
                    binding.videoView.start()
                    binding.videoView.visibility = View.VISIBLE
                    binding.imvAddVideo.visibility = View.GONE
                    binding.tvAddVideo.visibility = View.GONE
                    statusButtonDone(isValidInformationInput())
                }
            }
        }
    )

    override fun initializeViews() {
        ActivityCompat.requestPermissions(requireActivity(), permissions(), 1);
        statusButtonDone(isValidInformationInput())

        binding.edtName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                statusButtonDone(isValidInformationInput())
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                statusButtonDone(isValidInformationInput())
            }

            override fun afterTextChanged(text: Editable?) {
                statusButtonDone(isValidInformationInput())
            }

        })

        binding.edtNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                statusButtonDone(isValidInformationInput())
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                statusButtonDone(isValidInformationInput())
            }

            override fun afterTextChanged(s: Editable?) {
                statusButtonDone(isValidInformationInput())
            }
        })
    }

    override fun initializeEvents() {
        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.flAvatar.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            pickImage.launch(intent)
        }

        binding.rlAddVideo.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            pickVideo.launch(intent)
        }

        binding.buttonDone.setOnClickListener {
            if (isValidInformationInput()) {
                viewModel.saveData(
                    context = requireContext(),
                    contactIcon = encodeImage!!,
                    contactName = binding.edtName.text.toString().trim(),
                    contactNumber = binding.edtNumber.text.toString().trim(),
                    contactVideo = videoUri
                )
                activity?.supportFragmentManager?.beginTransaction()?.remove(this)
                    ?.commitNow()
            } else {
                AlertDialog.Builder(context)
                    .setTitle("Please fill in the blanks")
                    .setNegativeButton(android.R.string.cancel, null)
                    .show()
            }
        }
    }

    private fun isValidInformationInput(): Boolean {
        if (encodeImage == null) {
            return false
        } else if (binding.edtName.text != null
            && binding.edtName.text.toString().trim().isEmpty()
        ) {
            return false
        } else if (binding.edtNumber.text != null
            && binding.edtNumber.text.toString().trim().isEmpty()
        ) {
            return false
        } else if (videoUri == null) {
            return false
        }
        return true
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun statusButtonDone(isValidData: Boolean) {
        if (isValidData) {
            binding.buttonDone.background = requireContext().getDrawable(
                R.drawable.background_button_done_active
            )
            binding.buttonDone.setTextColor(requireContext().getColor(R.color.white))

        } else {
            binding.buttonDone.background = requireContext().getDrawable(
                R.drawable.background_button_done_inactive
            )
            binding.buttonDone.setTextColor(requireContext().getColor(R.color.color_717171))
        }
    }

    private fun getFilePathFromURI(imageUri: Uri?, context: Activity): String? {
        var filePath: String? = null
        val filePathColumn = arrayOf(MediaStore.Video.Media.DATA)
        val cursor = context.contentResolver.query(
            imageUri!!,
            filePathColumn, null, null, null
        )
        if (cursor != null) {
            filePath = try {
                cursor.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                cursor.getString(columnIndex)
            } finally {
                cursor.close()
            }
        }
        return filePath
    }
}