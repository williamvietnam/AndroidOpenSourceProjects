package com.remote.brands.lg.features

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import com.connectsdk.core.ChannelInfo
import com.connectsdk.service.capability.KeyControl
import com.connectsdk.service.capability.PowerControl
import com.connectsdk.service.capability.TVControl
import com.connectsdk.service.capability.TVControl.ChannelListListener
import com.connectsdk.service.command.ServiceCommandError
import com.connectsdk.service.command.ServiceSubscription
import com.remote.R
import com.remote.brands.lg.LGBaseFragment
import com.remote.brands.lg.util.TestResponseObject
import com.remote.brands.lg.widget.ChannelAdapter
import com.remote.databinding.FragmentTvBinding

class TVFragment : LGBaseFragment {

    private lateinit var binding: FragmentTvBinding

    var adapter: ChannelAdapter? = null

    var mode3DToggle = false
    var incomingCallToggle = false
    var testResponse: TestResponseObject? = null

    private var mCurrentChannelSubscription: ServiceSubscription<TVControl.ChannelListener>? = null

    constructor() : super()

    constructor(context: Context) : super(context) {
        testResponse = TestResponseObject()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ChannelAdapter(requireContext(), R.layout.channel_item)
        binding.channelListView.adapter = adapter

        buttons = arrayOf(
            binding.numberButton0,
            binding.numberButton1,
            binding.numberButton2,
            binding.numberButton3,
            binding.numberButton4,
            binding.numberButton5,
            binding.numberButton6,
            binding.numberButton7,
            binding.numberButton8,
            binding.numberButton9,
            binding.dashButton,
            binding.enterButton,
            binding.channelUpButton,
            binding.channelDownButton,
            binding.powerOffButton,
            binding.mode3DButton
        )

        mode3DToggle = false
        incomingCallToggle = false

        binding.channelListView.onItemClickListener = OnItemClickListener { arg0, _, arg2, _ ->
            val channelInfo = arg0.getItemAtPosition(arg2) as ChannelInfo
            getTVControl()!!.setChannel(channelInfo, null)
        }
    }

    override fun enableButtons() {
        super.enableButtons()
        if (getTv()!!.hasAnyCapability(KeyControl.KeyCode)) {
            binding.numberButton0.setOnClickListener(View.OnClickListener {
                getKeyControl()!!.sendKeyCode(
                    KeyControl.KeyCode.NUM_0,
                    null
                )
            })
            binding.numberButton1.setOnClickListener(View.OnClickListener {
                getKeyControl()!!.sendKeyCode(
                    KeyControl.KeyCode.NUM_1,
                    null
                )
            })
            binding.numberButton2.setOnClickListener(View.OnClickListener {
                getKeyControl()!!.sendKeyCode(
                    KeyControl.KeyCode.NUM_2,
                    null
                )
            })
            binding.numberButton3.setOnClickListener(View.OnClickListener {
                getKeyControl()!!.sendKeyCode(
                    KeyControl.KeyCode.NUM_3,
                    null
                )
            })
            binding.numberButton4.setOnClickListener(View.OnClickListener {
                getKeyControl()!!.sendKeyCode(
                    KeyControl.KeyCode.NUM_4,
                    null
                )
            })
            binding.numberButton5.setOnClickListener(View.OnClickListener {
                getKeyControl()!!.sendKeyCode(
                    KeyControl.KeyCode.NUM_5,
                    null
                )
            })
            binding.numberButton6.setOnClickListener(View.OnClickListener {
                getKeyControl()!!.sendKeyCode(
                    KeyControl.KeyCode.NUM_6,
                    null
                )
            })
            binding.numberButton7.setOnClickListener(View.OnClickListener {
                getKeyControl()!!.sendKeyCode(
                    KeyControl.KeyCode.NUM_7,
                    null
                )
            })
            binding.numberButton8.setOnClickListener(View.OnClickListener {
                getKeyControl()!!.sendKeyCode(
                    KeyControl.KeyCode.NUM_8,
                    null
                )
            })
            binding.numberButton9.setOnClickListener(View.OnClickListener {
                getKeyControl()!!.sendKeyCode(
                    KeyControl.KeyCode.NUM_9,
                    null
                )
            })
            binding.dashButton.setOnClickListener(View.OnClickListener {
                getKeyControl()!!.sendKeyCode(
                    KeyControl.KeyCode.DASH,
                    null
                )
            })
            binding.enterButton.setOnClickListener(View.OnClickListener {
                getKeyControl()!!.sendKeyCode(
                    KeyControl.KeyCode.ENTER,
                    null
                )
            })
        } else {
            disableButton(binding.numberButton0)
            disableButton(binding.numberButton1)
            disableButton(binding.numberButton2)
            disableButton(binding.numberButton3)
            disableButton(binding.numberButton4)
            disableButton(binding.numberButton5)
            disableButton(binding.numberButton6)
            disableButton(binding.numberButton7)
            disableButton(binding.numberButton8)
            disableButton(binding.numberButton9)
            disableButton(binding.dashButton)
            disableButton(binding.enterButton)
        }
        if (getTv()!!.hasCapability(TVControl.Channel_Up)) {
            binding.channelUpButton.setOnClickListener(View.OnClickListener {
                getTVControl()!!.channelUp(
                    null
                )
            })
        } else {
            disableButton(binding.channelUpButton)
        }
        if (getTv()!!.hasCapability(TVControl.Channel_Down)) {
            binding.channelDownButton.setOnClickListener(View.OnClickListener {
                getTVControl()!!.channelDown(
                    null
                )
            })
        } else {
            disableButton(binding.channelDownButton)
        }
        if (getTv()!!.hasCapability(PowerControl.Off)) {
            binding.powerOffButton.setOnClickListener(View.OnClickListener {
                testResponse = TestResponseObject(
                    true,
                    TestResponseObject.SuccessCode,
                    TestResponseObject.Power_OFF
                )
                getPowerControl()!!.powerOff(null)
            })
        } else {
            disableButton(binding.powerOffButton)
        }
        if (getTv()!!.hasCapability(TVControl.Set_3D)) {
            binding.mode3DButton.setOnClickListener(View.OnClickListener {
                if (getTv()!!.hasCapability(TVControl.Set_3D)) {
                    if (getTVControl() != null) {
                        mode3DToggle = !mode3DToggle
                        getTVControl()!!.set3DEnabled(mode3DToggle, null)
                    }
                }
            })
        } else {
            disableButton(binding.mode3DButton)
        }
        if (getTv()!!.hasCapability(TVControl.Channel_List)) {
            getTVControl()!!.getChannelList(object : ChannelListListener {
                override fun onSuccess(channelList: List<ChannelInfo?>) {
                    adapter!!.clear()
                    for (channel in channelList) adapter!!.add(channel)
                    adapter!!.sort()
                }

                override fun onError(arg0: ServiceCommandError) {}
            })
        }
        if (getTv()!!.hasCapability(TVControl.Channel_Subscribe)) {
            mCurrentChannelSubscription =
                getTVControl()!!.subscribeCurrentChannel(object : TVControl.ChannelListener {
                    override fun onSuccess(ch: ChannelInfo) {
                        adapter!!.setCurrentChannel(ch)
                        adapter!!.notifyDataSetChanged()
                        val position = adapter!!.getPosition(ch)
                        binding.channelListView.setSelection(position)
                    }

                    override fun onError(error: ServiceCommandError) {}
                })
        }
    }

    override fun disableButtons() {
        adapter!!.clear()
        if (mCurrentChannelSubscription != null) {
            mCurrentChannelSubscription?.unsubscribe()
            mCurrentChannelSubscription = null
        }
        super.disableButtons()
    }
}