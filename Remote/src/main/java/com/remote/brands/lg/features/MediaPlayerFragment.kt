package com.remote.brands.lg.features

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.connectsdk.core.MediaInfo
import com.connectsdk.core.SubtitleInfo
import com.connectsdk.device.ConnectableDevice
import com.connectsdk.service.capability.MediaControl
import com.connectsdk.service.capability.MediaControl.*
import com.connectsdk.service.capability.MediaPlayer
import com.connectsdk.service.capability.MediaPlayer.MediaInfoListener
import com.connectsdk.service.capability.MediaPlayer.MediaLaunchObject
import com.connectsdk.service.capability.PlaylistControl
import com.connectsdk.service.capability.VolumeControl
import com.connectsdk.service.capability.VolumeControl.VolumeListener
import com.connectsdk.service.capability.listeners.ResponseListener
import com.connectsdk.service.command.ServiceCommandError
import com.connectsdk.service.sessions.LaunchSession
import com.remote.brands.lg.LGBaseFragment
import com.remote.brands.lg.util.TestResponseObject
import com.remote.databinding.FragmentMediaPlayerBinding
import java.net.URL
import java.util.*
import java.util.concurrent.TimeUnit

class MediaPlayerFragment : LGBaseFragment {

    private lateinit var binding: FragmentMediaPlayerBinding

    companion object {
        const val URL_SUBTITLES_WEBVTT = "http://connectsdk.com/ConnectSDK.vtt"
        const val URL_SUBTITLE_SRT = "http://connectsdk.com/ConnectSDK.srt"
        const val URL_VIDEO_MP4 = "http://connectsdk.com/ConnectSDK.mp4"
        const val URL_IMAGE_ICON = "http://connectsdk.com/ConnectSDK_Logo.jpg"
    }

    var launchSession: LaunchSession? = null

    var mIsUserSeeking = false
    var mSeeking = false
    var mRefreshRunnable: Runnable? = null
    val REFRESH_INTERVAL_MS = TimeUnit.SECONDS.toMillis(1).toInt()
    var mHandler: Handler? = null
    var totalTimeDuration: Long = 0
    var mIsGettingPlayPosition = false

    var isPlayingImage = false
    var isPlaying = false

    private var mMediaControl: MediaControl? = null
    private var mPlaylistControl: PlaylistControl? = null

    private var refreshTimer: Timer? = null

    var testResponse: TestResponseObject? = null

    constructor() : super()

    constructor(context: Context) : super(context) {
        mIsUserSeeking = false
        mSeeking = false
        mIsGettingPlayPosition = false
        testResponse = TestResponseObject()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMediaPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true

        buttons = arrayOf(
            binding.photoButton,
            binding.videoButton,
            binding.audioButton,
            binding.playButton,
            binding.pauseButton,
            binding.stopButton,
            binding.rewindButton,
            binding.fastForwardButton,
            binding.closeButton,
            binding.mediaInfoButton,
            binding.playlistButton,
            binding.previousButton,
            binding.nextButton,
            binding.jumpButton,
            binding.loopingButton,
            binding.subtitlesButton
        )

        mHandler = Handler(Looper.getMainLooper())

    }

    override fun setTv(tv: ConnectableDevice?) {
        super.setTv(tv)
        if (tv == null) {
            stopUpdating()
            mMediaControl = null
            mPlaylistControl = null
        }
    }

    override fun onResume() {
        super.onResume()
        if (isPlaying) {
            startUpdating()
        }
    }

    override fun onPause() {
        stopUpdating()
        super.onPause()
        testResponse = TestResponseObject(
            true,
            TestResponseObject.SuccessCode,
            TestResponseObject.Paused_Media
        )
    }

    override fun enableButtons() {
        if (getTv()!!.hasCapability(MediaPlayer.Display_Image)) {
            binding.photoButton.isEnabled = true
            binding.photoButton.setOnClickListener(View.OnClickListener { showImage() })
        } else {
            disableButton(binding.photoButton)
        }
        totalTimeDuration = -1
        binding.loopingButton.isEnabled = getTv()!!.hasCapability(MediaPlayer.Loop)
        binding.subtitlesButton.setEnabled(
            getTv()!!.hasCapability(MediaPlayer.Subtitle_SRT)
                    || getTv()!!.hasCapability(MediaPlayer.Subtitle_WebVTT)
        )
        if (getTv()!!.hasCapability(MediaPlayer.Play_Video)) {
            binding.videoButton.setEnabled(true)
            binding.videoButton.setOnClickListener(View.OnClickListener { playVideo() })
        } else {
            disableButton(binding.videoButton)
        }
        if (getTv()!!.hasCapability(MediaPlayer.Play_Audio)) {
            binding.audioButton.setEnabled(true)
            binding.audioButton.setOnClickListener(View.OnClickListener { playAudio() })
        } else {
            disableButton(binding.audioButton)
        }
        if (getTv()!!.hasCapability(MediaPlayer.Play_Playlist)) {
            binding.playlistButton.setEnabled(true)
            binding.playlistButton.setOnClickListener(View.OnClickListener {
                playM3U()
                testResponse = TestResponseObject(
                    true,
                    TestResponseObject.SuccessCode,
                    TestResponseObject.Played_Playlist
                )
            })
        } else {
            disableButton(binding.playlistButton)
        }
        binding.volumeSeekBar.setEnabled(getTv()!!.hasCapability(VolumeControl.Volume_Set))
        binding.volumeSeekBar.setOnSeekBarChangeListener(volumeListener)
        if (getTv()!!.hasCapability(VolumeControl.Volume_Get)) {
            getVolumeControl()!!.getVolume(getVolumeListener)
        }
        if (getTv()!!.hasCapability(VolumeControl.Volume_Subscribe)) {
            getVolumeControl()!!.subscribeVolume(getVolumeListener)
        }
        if (getTv()!!.hasCapability(MediaPlayer.MediaInfo_Get)) {
            binding.mediaInfoButton.setEnabled(true)
            binding.mediaInfoButton.setOnClickListener(View.OnClickListener {
                getMediaPlayer()!!.getMediaInfo(
                    mediaInfoListener
                )
            })
        } else binding.mediaInfoButton.setEnabled(false)
        if (getTv()!!.hasCapability(MediaPlayer.MediaInfo_Subscribe)) {
            getMediaPlayer()!!.subscribeMediaInfo(mediaInfoListener)
        }
        if (!isPlaying || !isPlayingImage) disableMedia()
        if (isPlaying) enableMedia() else if (isPlayingImage) {
            binding.closeButton.setEnabled(true)
            binding.closeButton.setOnClickListener(closeListener)
            stopUpdating()
        }
    }

    private fun playAudio() {
        val mediaURL = "http://connectsdk.com/ConnectSDK.mp3"
        val iconURL = "http://connectsdk.com/ConnectSDK_Logo.jpg"
        val title = "Connect SDK"
        val description = "One SDK Eight Media Platforms"
        val mimeType = "audio/mp3"
        val shouldLoop: Boolean = binding.loopingButton.isChecked()
        val mediaInfo = MediaInfo.Builder(mediaURL, mimeType)
            .setTitle(title)
            .setDescription(description)
            .setIcon(iconURL)
            .build()
        getMediaPlayer()!!.playMedia(mediaInfo, shouldLoop, object : MediaPlayer.LaunchListener {
            override fun onError(error: ServiceCommandError) {
                Log.d("LG", "Error playing audio", error)
                stopMediaSession()
            }

            override fun onSuccess(`object`: MediaLaunchObject) {
                Log.d("LG", "Started playing audio")
                launchSession = `object`.launchSession
                testResponse = TestResponseObject(
                    true,
                    TestResponseObject.SuccessCode,
                    TestResponseObject.Play_Audio
                )
                mMediaControl = `object`.mediaControl
                mPlaylistControl = `object`.playlistControl
                stopUpdating()
                enableMedia()
                isPlaying = true
            }
        })
    }

    private fun playM3U() {
        val mediaURL =
            "http://ec2-54-201-108-205.us-west-2.compute.amazonaws.com/samples/media/example-m3u-playlist.m3u"
        val iconURL =
            "http://ec2-54-201-108-205.us-west-2.compute.amazonaws.com/samples/media/audioIcon.jpg"
        val title = "Playlist"
        val description = "Playlist description"
        val mimeType = "application/x-mpegurl"
        val shouldLoop: Boolean = binding.loopingButton.isChecked()
        val mediaInfo = MediaInfo.Builder(mediaURL, mimeType)
            .setTitle(title)
            .setDescription(description)
            .setIcon(iconURL)
            .build()
        getMediaPlayer()!!.playMedia(mediaInfo, shouldLoop, object : MediaPlayer.LaunchListener {
            override fun onError(error: ServiceCommandError) {
                Log.d("LG", "Error playing audio", error)
                stopMediaSession()
            }

            override fun onSuccess(`object`: MediaLaunchObject) {
                Log.d("LG", "Started playing playlist")
                launchSession = `object`.launchSession
                mMediaControl = `object`.mediaControl
                mPlaylistControl = `object`.playlistControl
                stopUpdating()
                enableMedia()
                isPlaying = true
            }
        })
    }

    private fun showImage() {
        disableMedia()
        val imagePath = "http://connectsdk.com/ConnectSDK.jpg"
        val mimeType = "image/jpeg"
        val title = "Connect SDK"
        val description = "One SDK Eight Media Platforms"
        val icon = "http://connectsdk.com/ConnectSDK_Logo.jpg"
        val mediaInfo = MediaInfo.Builder(imagePath, mimeType)
            .setTitle(title)
            .setDescription(description)
            .setIcon(icon)
            .build()
        getMediaPlayer()!!.displayImage(mediaInfo, object : MediaPlayer.LaunchListener {
            override fun onError(error: ServiceCommandError) {
                Log.e("Error", "Error displaying Image", error)
                stopMediaSession()
            }

            override fun onSuccess(`object`: MediaLaunchObject) {
                launchSession = `object`.launchSession
                binding.closeButton.isEnabled = true
                testResponse = TestResponseObject(
                    true, TestResponseObject.SuccessCode,
                    TestResponseObject.Display_image
                )
                binding.closeButton.setOnClickListener(closeListener)
                stopUpdating()
                isPlayingImage = true
            }
        })
    }

    private fun playVideo() {
        val shouldLoop: Boolean = binding.loopingButton.isChecked()
        var subtitleBuilder: SubtitleInfo.Builder? = null
        if (binding.subtitlesButton.isChecked()) {
            subtitleBuilder = SubtitleInfo.Builder(
                if (getTv()!!.hasCapability(MediaPlayer.Subtitle_WebVTT)) URL_SUBTITLES_WEBVTT else URL_SUBTITLE_SRT
            )
            subtitleBuilder.setLabel("English").setLanguage("en")
        }
        val mediaInfo = MediaInfo.Builder(URL_VIDEO_MP4, "video/mp4")
            .setTitle("Connect SDK")
            .setDescription("One SDK Eight Media Platforms")
            .setIcon(URL_IMAGE_ICON)
            .setSubtitleInfo((subtitleBuilder?.build())!!)
            .build()
        getMediaPlayer()!!.playMedia(mediaInfo, shouldLoop, object : MediaPlayer.LaunchListener {
            override fun onError(error: ServiceCommandError) {
                Log.e("Error", "Error playing video", error)
                stopMediaSession()
            }

            override fun onSuccess(`object`: MediaLaunchObject) {
                launchSession = `object`.launchSession
                testResponse = TestResponseObject(
                    true, TestResponseObject.SuccessCode,
                    TestResponseObject.Play_Video
                )
                mMediaControl = `object`.mediaControl
                mPlaylistControl = `object`.playlistControl
                stopUpdating()
                enableMedia()
                isPlaying = true
            }
        })
    }


    private fun stopMediaSession() {
        // don't call launchSession.close() here, currently it can close
        // a different web app in WebOS
        if (launchSession != null) {
            launchSession = null
            stopUpdating()
            disableMedia()
            isPlayingImage = false
            isPlaying = isPlayingImage
        }
    }

    override fun disableButtons() {
        binding.streamSeekBar.setEnabled(false)
        binding.volumeSeekBar.setEnabled(false)
        binding.volumeSeekBar.setOnSeekBarChangeListener(null)
        binding.streamPosition.setEnabled(false)
        binding.streamDuration.setEnabled(false)
        binding.mediaInfoTextView.setText("")
        binding.mediaInfoImageView.setImageBitmap(null)
        binding.positionText.setEnabled(false)
        binding.loopingButton.setChecked(false)
        binding.subtitlesButton.setEnabled(false)
        super.disableButtons()
    }

    fun onSeekBarMoved(position: Long) {
        if (mMediaControl != null && getTv()!!.hasCapability(MediaControl.Seek)) {
            mSeeking = true
            mMediaControl?.seek(position, object : ResponseListener<Any?> {
                override fun onSuccess(response: Any?) {
                    Log.d("LG", "Success on Seeking")
                    mSeeking = false
                    startUpdating()
                }

                override fun onError(error: ServiceCommandError) {
                    Log.w("Connect SDK", "Unable to seek: " + error.code)
                    mSeeking = false
                    startUpdating()
                }
            })
        }
    }

    fun enableMedia() {
        binding.playButton.setEnabled(getTv()!!.hasCapability(MediaControl.Play))
        binding.pauseButton.setEnabled(getTv()!!.hasCapability(MediaControl.Pause))
        binding.stopButton.setEnabled(getTv()!!.hasCapability(MediaControl.Stop))
        binding.rewindButton.setEnabled(getTv()!!.hasCapability(MediaControl.Rewind))
        binding.fastForwardButton.setEnabled(getTv()!!.hasCapability(MediaControl.FastForward))
        binding.streamSeekBar.setEnabled(getTv()!!.hasCapability(MediaControl.Seek))
        binding.closeButton.setEnabled(getTv()!!.hasCapability(MediaPlayer.Close))
        binding.previousButton.setEnabled(getTv()!!.hasCapability(PlaylistControl.Previous))
        binding.nextButton.setEnabled(getTv()!!.hasCapability(PlaylistControl.Next))
        binding.jumpButton.setEnabled(getTv()!!.hasCapability(PlaylistControl.JumpToTrack))
        binding.positionText.setEnabled(getTv()!!.hasCapability(PlaylistControl.JumpToTrack))
        binding.fastForwardButton.setOnClickListener(fastForwardListener)
        binding.streamSeekBar.setOnSeekBarChangeListener(seekListener)
        binding.rewindButton.setOnClickListener(rewindListener)
        binding.stopButton.setOnClickListener(stopListener)
        binding.playButton.setOnClickListener(playListener)
        binding.pauseButton.setOnClickListener(pauseListener)
        binding.previousButton.setOnClickListener(previousListener)
        binding.nextButton.setOnClickListener(nextListener)
        binding.jumpButton.setOnClickListener(jumpListener)
        binding.closeButton.setOnClickListener(closeListener)
        if (getTv()!!.hasCapability(MediaControl.PlayState_Subscribe) && !isPlaying) {
            mMediaControl!!.subscribePlayState(playStateListener)
        } else {
            if (mMediaControl != null) {
                mMediaControl?.getDuration(durationListener)
            }
            startUpdating()
        }
    }

    fun disableMedia() {
        binding.closeButton.setEnabled(false)
        binding.closeButton.setOnClickListener(null)
        stopMedia()
    }

    fun stopMedia() {
        binding.playButton.setEnabled(false)
        binding.playButton.setOnClickListener(null)
        binding.pauseButton.setEnabled(false)
        binding.pauseButton.setOnClickListener(null)
        binding.stopButton.setEnabled(false)
        binding.stopButton.setOnClickListener(null)
        binding.rewindButton.setEnabled(false)
        binding.rewindButton.setOnClickListener(null)
        binding.fastForwardButton.setEnabled(false)
        binding.fastForwardButton.setOnClickListener(null)
        binding.previousButton.setEnabled(false)
        binding.previousButton.setOnClickListener(null)
        binding.nextButton.setEnabled(false)
        binding.nextButton.setOnClickListener(null)
        binding.jumpButton.setEnabled(false)
        binding.jumpButton.setOnClickListener(null)
        binding.positionText.setEnabled(false)
        binding.streamSeekBar.setEnabled(false)
        binding.streamSeekBar.setOnSeekBarChangeListener(null)
        binding.streamSeekBar.setProgress(0)
        binding.streamPosition.setText("--:--:--")
        binding.streamDuration.text = "--:--:--"
        totalTimeDuration = -1
    }

    var playListener: View.OnClickListener = View.OnClickListener {
        if (mMediaControl != null) mMediaControl?.play(null)
        testResponse = TestResponseObject(
            true,
            TestResponseObject.SuccessCode,
            TestResponseObject.Played_Media
        )
    }

    var pauseListener: View.OnClickListener = View.OnClickListener {
        if (mMediaControl != null) mMediaControl?.pause(null)
        testResponse = TestResponseObject(
            true,
            TestResponseObject.SuccessCode,
            TestResponseObject.Paused_Media
        )
    }

    var previousListener: View.OnClickListener = View.OnClickListener {
        if (mPlaylistControl != null) mPlaylistControl?.previous(null)
        testResponse =
            TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.Previous)
    }

    var nextListener: View.OnClickListener = View.OnClickListener {
        if (mPlaylistControl != null) mPlaylistControl?.next(null)
        testResponse =
            TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.Next)
    }

    var jumpListener: View.OnClickListener = View.OnClickListener {
        if (mPlaylistControl != null) {
            mPlaylistControl?.jumpToTrack(binding.positionText.text.toString().toLong(), null)
            testResponse =
                TestResponseObject(true, TestResponseObject.SuccessCode, TestResponseObject.Jump)
        }
    }


    var closeListener: View.OnClickListener = View.OnClickListener {
        if (getMediaPlayer() != null) {
            if (launchSession != null) launchSession?.close(null)
            launchSession = null
            disableMedia()
            stopUpdating()
            isPlayingImage = false
            isPlaying = isPlayingImage
            testResponse = TestResponseObject(
                true,
                TestResponseObject.SuccessCode,
                TestResponseObject.Closed_Media
            )
        }
    }

    var stopListener: View.OnClickListener = View.OnClickListener {
        if (mMediaControl != null) mMediaControl?.stop(object : ResponseListener<Any?> {
            override fun onSuccess(response: Any?) {
                stopMedia()
                stopUpdating()
                testResponse = TestResponseObject(
                    true,
                    TestResponseObject.SuccessCode,
                    TestResponseObject.Stopped_Media
                )
                isPlaying = false
                isPlayingImage = true
            }

            override fun onError(error: ServiceCommandError) {}
        })
    }

    var rewindListener: View.OnClickListener = View.OnClickListener {
        if (mMediaControl != null) mMediaControl?.rewind(null)
        testResponse = TestResponseObject(
            true,
            TestResponseObject.SuccessCode,
            TestResponseObject.Rewind_Media
        )
    }

    var fastForwardListener: View.OnClickListener = View.OnClickListener {
        if (mMediaControl != null) mMediaControl?.fastForward(null)
        testResponse = TestResponseObject(
            true,
            TestResponseObject.SuccessCode,
            TestResponseObject.FastForward_Media
        )
    }

    var seekListener: OnSeekBarChangeListener = object : OnSeekBarChangeListener {
        override fun onStopTrackingTouch(seekBar: SeekBar) {
            mIsUserSeeking = false
            binding.streamSeekBar.secondaryProgress = 0
            onSeekBarMoved(seekBar.progress.toLong())
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
            mIsUserSeeking = true
            binding.streamSeekBar.secondaryProgress = seekBar.progress
            stopUpdating()
        }

        override fun onProgressChanged(arg0: SeekBar, arg1: Int, arg2: Boolean) {}
    }

    var volumeListener: OnSeekBarChangeListener = object : OnSeekBarChangeListener {
        override fun onStopTrackingTouch(arg0: SeekBar) {}
        override fun onStartTrackingTouch(arg0: SeekBar) {}
        override fun onProgressChanged(seekBar: SeekBar, position: Int, fromUser: Boolean) {
            if (fromUser) getVolumeControl()!!.setVolume(
                binding.volumeSeekBar.progress.toFloat() / 100.0f, null
            )
        }
    }

    var getVolumeListener: VolumeListener = object : VolumeListener {
        override fun onError(error: ServiceCommandError) {
            Log.d("LG", "Error getting Volume: $error")
        }

        override fun onSuccess(`object`: Float) {
            binding.volumeSeekBar.progress = (`object` * 100.0f).toInt()
        }
    }

    var playStateListener: PlayStateListener = object : PlayStateListener {
        override fun onError(error: ServiceCommandError) {
            Log.d("LG", "Playstate Listener error = $error")
        }

        override fun onSuccess(playState: PlayStateStatus) {
            Log.d("LG", "Playstate changed | playState = $playState")
            when (playState) {
                PlayStateStatus.Playing -> {
                    startUpdating()
                    if (mMediaControl != null && getTv()!!.hasCapability(MediaControl.Duration)) {
                        mMediaControl?.getDuration(durationListener)
                    }
                }

                PlayStateStatus.Finished -> {
                    binding.streamPosition.setText("--:--")
                    binding.streamDuration.setText("--:--")
                    binding.streamSeekBar.setProgress(0)
                    stopUpdating()
                }

                else -> stopUpdating()
            }
        }
    }

    private fun startUpdating() {
        if (refreshTimer != null) {
            refreshTimer?.cancel()
            refreshTimer = null
        }
        refreshTimer = Timer()
        refreshTimer?.schedule(object : TimerTask() {
            override fun run() {
                Log.d("LG", "Updating information")
                if ((mMediaControl != null) && (getTv() != null) && getTv()!!.hasCapability(
                        MediaControl.Position
                    )
                ) {
                    mMediaControl?.getPosition(positionListener)
                }
                if (((mMediaControl != null
                            ) && (getTv() != null
                            ) && getTv()!!.hasCapability(MediaControl.Duration)
                            && !getTv()!!.hasCapability(MediaControl.PlayState_Subscribe)
                            && (totalTimeDuration <= 0))
                ) {
                    mMediaControl?.getDuration(durationListener)
                }
            }
        }, 0, REFRESH_INTERVAL_MS.toLong())
    }

    private fun stopUpdating() {
        if (refreshTimer == null) return
        refreshTimer?.cancel()
        refreshTimer = null
    }

    private val positionListener: PositionListener = object : PositionListener {
        override fun onError(error: ServiceCommandError) {}
        override fun onSuccess(position: Long) {
            binding.streamPosition.setText(formatTime(position.toInt().toLong()))
            binding.streamSeekBar.setProgress(position.toInt())
        }
    }

    private val durationListener: DurationListener = object : DurationListener {
        override fun onError(error: ServiceCommandError) {}
        override fun onSuccess(duration: Long) {
            totalTimeDuration = duration
            binding.streamSeekBar.setMax(duration.toInt())
            binding.streamDuration.setText(formatTime(duration.toInt().toLong()))
        }
    }

    private val mediaInfoListener: MediaInfoListener = object : MediaInfoListener {
        override fun onSuccess(mediaInfo: MediaInfo) {
            var text = mediaInfo.title
            text += "\n"
            text += mediaInfo.description
            binding.mediaInfoTextView.setText(text)
            val stringUrl = mediaInfo.images[0].url
            if (stringUrl != null) DownloadImageTask(binding.mediaInfoImageView).execute(stringUrl)
        }

        override fun onError(error: ServiceCommandError) {}
    }

    private fun formatTime(millisec: Long): String? {
        var seconds = (millisec / 1000).toInt()
        val hours = seconds / (60 * 60)
        seconds %= (60 * 60)
        val minutes = seconds / 60
        seconds %= 60
        val time: String
        if (hours > 0) {
            time = String.format(Locale.US, "%d:%02d:%02d", hours, minutes, seconds)
        } else {
            time = String.format(Locale.US, "%d:%02d", minutes, seconds)
        }
        return time
    }

    private class DownloadImageTask(var bmImage: ImageView) :
        AsyncTask<String?, Void?, Bitmap?>() {
        override fun doInBackground(vararg urls: String?): Bitmap? {
            val urldisplay = urls[0]
            var mIcon11: Bitmap? = null
            try {
                Log.d("", urldisplay!!)
                val `in` = URL(urldisplay).openStream()
                mIcon11 = BitmapFactory.decodeStream(`in`)
            } catch (e: Exception) {
                Log.e("Error", (e.message)!!)
                e.printStackTrace()
            }
            return mIcon11
        }

        override fun onPostExecute(result: Bitmap?) {
            bmImage.setImageBitmap(result)
        }
    }
}