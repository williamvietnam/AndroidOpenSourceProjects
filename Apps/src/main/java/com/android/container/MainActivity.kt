package com.android.container

import android.Manifest.permission.*
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.media.MediaScannerConnection
import android.media.projection.MediaProjectionManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.android.R
import com.android.base.BaseActivity
import com.android.databinding.ActivityMainBinding
import com.hbisoft.hbrecorder.HBRecorder
import com.hbisoft.hbrecorder.HBRecorderCodecInfo
import com.hbisoft.hbrecorder.HBRecorderListener
import java.io.File
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), HBRecorderListener {

    //--------------------start code for ATTRIBUTES of screen recording function--------------------
    companion object {
        const val SCREEN_RECORD_REQUEST_CODE = 777
        const val PERMISSION_REQ_ID_RECORD_AUDIO = 22
        const val PERMISSION_REQ_POST_NOTIFICATIONS = 33
        const val PERMISSION_REQ_ID_WRITE_EXTERNAL_STORAGE = PERMISSION_REQ_ID_RECORD_AUDIO + 1
    }

    var hbRecorder: HBRecorder? = null
    var hasPermissions: Boolean = false
    var resolver: ContentResolver? = null
    var contentValues: ContentValues? = null
    var videoUri: Uri? = null
    //--------------------end code for ATTRIBUTES of screen recording function----------------------


    override fun createViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): MainViewModel {
        return ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun initializeViews() {
        // init navigation component
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as NavHostFragment
        val navController = navHostFragment.navController

        // check create contactsDatabase from json
        viewModel.saveContactsListFromJsonToDatabase(this)

        recordingScreen()
    }

    override fun initializeEvents() {

    }

    private fun recordingScreen() {
        //------------------------Start code for screen recording function--------------------------
        hbRecorder = HBRecorder(this, this)
        val hbRecorderCodecInfo = HBRecorderCodecInfo()
        val mWidth = hbRecorder!!.defaultWidth
        val mHeight = hbRecorder!!.defaultHeight
        val mMimeType = "video/avc"
        val mFPS = 30
        if (hbRecorderCodecInfo.isMimeTypeSupported(mMimeType)) {
            val defaultVideoEncoder = hbRecorderCodecInfo.getDefaultVideoEncoderName(mMimeType)
            val isSizeAndFramerateSupported = hbRecorderCodecInfo.isSizeAndFramerateSupported(
                mWidth,
                mHeight,
                mFPS,
                mMimeType,
                Configuration.ORIENTATION_PORTRAIT
            )
            val supportedVideoMimeTypes = hbRecorderCodecInfo.supportedVideoMimeTypes
            val supportedAudioMimeTypes = hbRecorderCodecInfo.supportedAudioMimeTypes
            val supportedVideoFormats = hbRecorderCodecInfo.supportedVideoFormats
            for (j in supportedVideoFormats.indices) {
                Log.e(
                    "HBRecorderCodecInfo",
                    "Available Video Formats : " + supportedVideoFormats[j]
                )
            }
        } else {
            Log.e("HBRecorderCodecInfo", "MimeType not supported")
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(POST_NOTIFICATIONS, PERMISSION_REQ_POST_NOTIFICATIONS)
                && checkSelfPermission(RECORD_AUDIO, PERMISSION_REQ_ID_RECORD_AUDIO)
            ) {
                this@MainActivity.hasPermissions = true
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (checkSelfPermission(RECORD_AUDIO, PERMISSION_REQ_ID_RECORD_AUDIO)) {
                this@MainActivity.hasPermissions = true
            }
        } else {
            if (checkSelfPermission(RECORD_AUDIO, PERMISSION_REQ_ID_RECORD_AUDIO)
                && checkSelfPermission(
                    WRITE_EXTERNAL_STORAGE, PERMISSION_REQ_ID_WRITE_EXTERNAL_STORAGE
                )
            ) {
                this@MainActivity.hasPermissions = true
            }
        }
        //------------------------End code for screen recording function--------------------------
    }

    //-------------------------Start code for METHODS of screen recording---------------------------
    private fun createdFolder() {
        val f1 = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES),
            "HBRecorder"
        )
        if (!f1.exists()) {
            if (f1.mkdirs()) {
                Log.i("Folder ", "created")
            }
        }
    }

    private fun checkSelfPermission(permission: String, requestCode: Int): Boolean {
        if (ContextCompat.checkSelfPermission(
                this, permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
            return false
        }
        return true
    }

    fun startRecordingScreen() {
        hbRecorder?.setAudioBitrate(128000)
        hbRecorder?.setAudioSamplingRate(44100)
        hbRecorder?.recordHDVideo(true)
        hbRecorder?.isAudioEnabled(true)
        val mediaProjectionManager = getSystemService(
            MEDIA_PROJECTION_SERVICE
        ) as MediaProjectionManager
        val permissionIntent = mediaProjectionManager.createScreenCaptureIntent()
        startActivityForResult(permissionIntent!!, SCREEN_RECORD_REQUEST_CODE)
    }

    //Handle permissions
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQ_POST_NOTIFICATIONS -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkSelfPermission(RECORD_AUDIO, PERMISSION_REQ_ID_RECORD_AUDIO)
            } else {
                hasPermissions = false
                showLongToast("No permission for $POST_NOTIFICATIONS")
            }

            PERMISSION_REQ_ID_RECORD_AUDIO -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkSelfPermission(
                    WRITE_EXTERNAL_STORAGE,
                    PERMISSION_REQ_ID_WRITE_EXTERNAL_STORAGE
                )
            } else {
                hasPermissions = false
                showLongToast("No permission for $RECORD_AUDIO")
            }

            PERMISSION_REQ_ID_WRITE_EXTERNAL_STORAGE -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                hasPermissions = true
            } else {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    hasPermissions = true
                } else {
                    hasPermissions = false
                    showLongToast("No permission for $WRITE_EXTERNAL_STORAGE")
                }
            }

            else -> {}
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SCREEN_RECORD_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                setOutputPath()
                //Start screen recording
                hbRecorder!!.startScreenRecording(data, resultCode)
            }
        }
    }

    private fun setOutputPath() {
        val filename: String = generateFileName()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            resolver = contentResolver
            contentValues = ContentValues()
            contentValues?.put(MediaStore.Video.Media.RELATIVE_PATH, "Movies/" + "HBRecorder")
            contentValues?.put(MediaStore.Video.Media.TITLE, filename)
            contentValues?.put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
            contentValues?.put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
            videoUri = resolver?.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues)
            //FILE NAME SHOULD BE THE SAME
            hbRecorder!!.fileName = filename
            hbRecorder!!.setOutputUri(videoUri)
        } else {
            createdFolder()
            hbRecorder!!.setOutputPath(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)
                    .toString() + "/HBRecorder"
            )
        }
    }

    //Generate a timestamp to be used as a file name
    private fun generateFileName(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault())
        val curDate = Date(System.currentTimeMillis())
        return formatter.format(curDate).replace(" ", "")
    }

    private fun showLongToast(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private fun updateGalleryUri() {
        contentValues!!.clear()
        contentValues!!.put(MediaStore.Video.Media.IS_PENDING, 0)
        contentResolver.update(videoUri!!, contentValues, null, null)
    }

    private fun refreshGalleryFile() {
        MediaScannerConnection.scanFile(
            this, arrayOf(hbRecorder!!.filePath), null
        ) { path, uri ->
            Log.i("ExternalStorage", "Scanned $path:")
            Log.i("ExternalStorage", "-> uri=$uri")
        }
    }

    override fun HBRecorderOnStart() {
        Log.e("HBRecorder", "HBRecorderOnStart called")
    }

    override fun HBRecorderOnComplete() {
        showLongToast("Saved Successfully")
        //Update gallery depending on SDK Level
        if (hbRecorder!!.wasUriSet()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                updateGalleryUri()
            } else {
                refreshGalleryFile()
            }
        } else {
            refreshGalleryFile()
        }

    }

    override fun HBRecorderOnError(errorCode: Int, reason: String?) {}

    override fun HBRecorderOnPause() {}

    override fun HBRecorderOnResume() {}

    //--------------------------End code for METHODS of screen recording----------------------------

}