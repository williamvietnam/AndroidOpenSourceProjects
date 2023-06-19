package com.android.remotes.lg.features

import android.R
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.android.databinding.FragmentSystemBinding
import com.remote.brands.lg.LGBaseFragment
import com.android.remotes.lg.util.TestResponseObject
import com.connectsdk.core.ExternalInputInfo
import com.connectsdk.service.capability.ExternalInputControl
import com.connectsdk.service.capability.ExternalInputControl.ExternalInputListListener
import com.connectsdk.service.capability.Launcher.AppLaunchListener
import com.connectsdk.service.capability.MediaControl
import com.connectsdk.service.capability.VolumeControl
import com.connectsdk.service.capability.VolumeControl.MuteListener
import com.connectsdk.service.capability.VolumeControl.VolumeListener
import com.connectsdk.service.command.ServiceCommandError
import com.connectsdk.service.command.ServiceSubscription
import com.connectsdk.service.sessions.LaunchSession

class SystemFragment : LGBaseFragment {

    private lateinit var binding: FragmentSystemBinding

    var adapter: ArrayAdapter<String>? = null

    var inputPickerSession: LaunchSession? = null

    var testResponse: TestResponseObject? = null

    private var mVolumeSubscription: ServiceSubscription<VolumeListener>? = null
    private var mMuteSubscription: ServiceSubscription<MuteListener>? = null

    constructor() : super()

    constructor(context: Context) : super(context) {
        testResponse = TestResponseObject()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSystemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ArrayAdapter(requireContext(), R.layout.simple_list_item_1)
        binding.inputListView.adapter = adapter

        binding.volumeSlider.max = 100

        binding.inputListView.onItemClickListener = OnItemClickListener { arg0, arg1, arg2, arg3 ->
            val input = arg0.getItemAtPosition(arg2) as String
            if (getTv()!!.hasCapability(ExternalInputControl.Set)) {
                val inputInfo = ExternalInputInfo()
                inputInfo.id = input
                getExternalInputControl()!!.setExternalInput(inputInfo, null)
            }
        }

        buttons = arrayOf(
            binding.inputPickerButton,
            binding.volumeUpButton,
            binding.volumeDownButton,
            binding.muteToggle,
            binding.pauseButton,
            binding.playButton,
            binding.stopButton,
            binding.rewindButton,
            binding.fastForwardButton
        )
    }

    override fun enableButtons() {
        super.enableButtons()
        if (getTv()!!.hasCapability(ExternalInputControl.List)) getExternalInputControl()!!.getExternalInputList(
            externalInputListener
        )
        binding.volumeSlider.isEnabled = getTv()!!.hasCapability(VolumeControl.Volume_Set)
        binding.inputPickerButton.isEnabled =
            getTv()!!.hasCapability(ExternalInputControl.Picker_Launch)
        binding.muteToggle.isEnabled = getTv()!!.hasCapability(VolumeControl.Mute_Set)
        binding.volumeUpButton.isEnabled = getTv()!!.hasCapability(VolumeControl.Volume_Up_Down)
        binding.volumeDownButton.isEnabled = getTv()!!.hasCapability(VolumeControl.Volume_Up_Down)
        binding.playButton.isEnabled = getTv()!!.hasCapability(MediaControl.Play)
        binding.pauseButton.isEnabled = getTv()!!.hasCapability(MediaControl.Pause)
        binding.stopButton.isEnabled = getTv()!!.hasCapability(MediaControl.Stop)
        binding.rewindButton.isEnabled = getTv()!!.hasCapability(MediaControl.Rewind)
        binding.fastForwardButton.isEnabled = getTv()!!.hasCapability(MediaControl.FastForward)
        if (getTv()!!.hasCapability(VolumeControl.Volume_Subscribe)) mVolumeSubscription =
            getVolumeControl()!!.subscribeVolume(volumeListener)
        if (getTv()!!.hasCapability(VolumeControl.Mute_Subscribe)) mMuteSubscription =
            getVolumeControl()!!.subscribeMute(muteListener)
        binding.inputPickerButton.setOnClickListener(inputPickerClickListener)
        binding.volumeUpButton.setOnClickListener(volumeChangedClickListener)
        binding.volumeDownButton.setOnClickListener(volumeChangedClickListener)
        binding.muteToggle.setOnClickListener(muteToggleClickListener)
        binding.volumeSlider.setOnSeekBarChangeListener(volumeSeekListener)
        binding.playButton.setOnClickListener(playClickListener)
        binding.pauseButton.setOnClickListener(pauseClickListener)
        binding.stopButton.setOnClickListener(stopClickListener)
        binding.rewindButton.setOnClickListener(rewindClickListener)
        binding.fastForwardButton.setOnClickListener(fastForwardClickListener)
    }

    private val muteToggleClickListener = View.OnClickListener {
        getVolumeControl()!!.setMute(binding.muteToggle.isChecked, null)
        if (binding.muteToggle.isChecked) {
            testResponse = TestResponseObject(
                true,
                TestResponseObject.SuccessCode,
                TestResponseObject.Muted_Media
            )
        } else if (!binding.muteToggle.isChecked) {
            testResponse = TestResponseObject(
                true,
                TestResponseObject.SuccessCode,
                TestResponseObject.UnMuted_Media
            )
        }
    }

    private val volumeChangedClickListener =
        View.OnClickListener { v ->
            when (v.id) {
                com.android.R.id.volumeDownButton -> {
                    getVolumeControl()!!.volumeDown(null)
                    testResponse = TestResponseObject(
                        true,
                        TestResponseObject.SuccessCode,
                        TestResponseObject.VolumeDown
                    )
                }

                com.android.R.id.volumeUpButton -> {
                    getVolumeControl()!!.volumeUp(null)
                    testResponse = TestResponseObject(
                        true,
                        TestResponseObject.SuccessCode,
                        TestResponseObject.VolumeUp
                    )
                }
            }
        }

    private val inputPickerClickListener = View.OnClickListener {
        if (binding.inputPickerButton.isSelected()) {
            if (inputPickerSession != null) {
                binding.inputPickerButton.setSelected(false)
                getExternalInputControl()!!.closeInputPicker(inputPickerSession, null)
            }
        } else {
            binding.inputPickerButton.setSelected(true)
            if (getExternalInputControl() != null) {
                getExternalInputControl()!!.launchInputPicker(object : AppLaunchListener {
                    override fun onError(error: ServiceCommandError) {}
                    override fun onSuccess(`object`: LaunchSession) {
                        inputPickerSession = `object`
                        testResponse = TestResponseObject(
                            true,
                            TestResponseObject.SuccessCode,
                            TestResponseObject.InputPickerVisible
                        )
                    }
                })
            }
        }
    }

    private val externalInputListener: ExternalInputListListener =
        object : ExternalInputListListener {
            override fun onSuccess(externalInputList: List<ExternalInputInfo>) {
                adapter!!.clear()
                for (i in externalInputList.indices) {
                    val input = externalInputList[i]
                    val deviceId = input.id
                    adapter!!.add(deviceId)
                }
            }

            override fun onError(arg0: ServiceCommandError) {}
        }

    private val volumeListener: VolumeListener = object : VolumeListener {
        override fun onSuccess(volume: Float) {
            binding.volumeSlider.progress = (volume * 100).toInt()
        }

        override fun onError(error: ServiceCommandError) {
            Log.d("LG", "Error subscribing to volume: $error")
        }
    }

    private val muteListener: MuteListener = object : MuteListener {
        override fun onSuccess(`object`: Boolean) {
            binding.muteToggle.isChecked = `object`
        }

        override fun onError(error: ServiceCommandError) {
            Log.d("LG", "Error subscribing to mute: $error")
        }
    }

    private val volumeSeekListener: OnSeekBarChangeListener = object : OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            if (fromUser) {
                val fVol = (progress / 100.0).toFloat()
                getVolumeControl()!!.setVolume(fVol, null)
            }
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onStopTrackingTouch(seekBar: SeekBar) {}
    }

    private val playClickListener = View.OnClickListener { getMediaControl()!!.play(null) }

    private val pauseClickListener = View.OnClickListener { getMediaControl()!!.pause(null) }

    private val stopClickListener = View.OnClickListener { getMediaControl()!!.stop(null) }

    private val rewindClickListener = View.OnClickListener { getMediaControl()!!.rewind(null) }

    private val fastForwardClickListener =
        View.OnClickListener { getMediaControl()!!.fastForward(null) }

    override fun disableButtons() {
        adapter?.clear()
        binding.volumeSlider.isEnabled = false
        binding.volumeSlider.setOnSeekBarChangeListener(null)
        if (mVolumeSubscription != null) {
            mVolumeSubscription?.unsubscribe()
            mVolumeSubscription = null
        }
        if (mMuteSubscription != null) {
            mMuteSubscription?.unsubscribe()
            mMuteSubscription = null
        }
        super.disableButtons()
    }
}