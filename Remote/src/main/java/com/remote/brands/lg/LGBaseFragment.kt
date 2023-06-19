package com.remote.brands.lg

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import androidx.fragment.app.Fragment
import com.connectsdk.device.ConnectableDevice
import com.connectsdk.service.capability.ExternalInputControl
import com.connectsdk.service.capability.KeyControl
import com.connectsdk.service.capability.Launcher
import com.connectsdk.service.capability.MediaControl
import com.connectsdk.service.capability.MediaPlayer
import com.connectsdk.service.capability.MouseControl
import com.connectsdk.service.capability.PowerControl
import com.connectsdk.service.capability.TVControl
import com.connectsdk.service.capability.TextInputControl
import com.connectsdk.service.capability.ToastControl
import com.connectsdk.service.capability.VolumeControl
import com.connectsdk.service.capability.WebAppLauncher

abstract class LGBaseFragment : Fragment {
    private var mTv: ConnectableDevice? = null
    private var launcher: Launcher? = null
    private var mediaPlayer: MediaPlayer? = null
    private var mediaControl: MediaControl? = null
    private var tvControl: TVControl? = null
    private var volumeControl: VolumeControl? = null
    private var toastControl: ToastControl? = null
    private var mouseControl: MouseControl? = null
    private var textInputControl: TextInputControl? = null
    private var powerControl: PowerControl? = null
    private var externalInputControl: ExternalInputControl? = null
    private var keyControl: KeyControl? = null
    private var webAppLauncher: WebAppLauncher? = null
    var buttons: Array<Button>? = null
    var mContext: Context? = null

    constructor() : super()

    constructor(context: Context) : super() {
        this.mContext = context
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setTv(mTv)
    }

    open fun setTv(tv: ConnectableDevice?) {
        mTv = tv
        if (tv == null) {
            launcher = null
            mediaPlayer = null
            mediaControl = null
            tvControl = null
            volumeControl = null
            toastControl = null
            textInputControl = null
            mouseControl = null
            externalInputControl = null
            powerControl = null
            keyControl = null
            webAppLauncher = null
            disableButtons()
        } else {
            launcher = mTv!!.getCapability(Launcher::class.java)
            mediaPlayer = mTv!!.getCapability(MediaPlayer::class.java)
            mediaControl = mTv!!.getCapability(MediaControl::class.java)
            tvControl = mTv!!.getCapability(TVControl::class.java)
            volumeControl = mTv!!.getCapability(VolumeControl::class.java)
            toastControl = mTv!!.getCapability(ToastControl::class.java)
            textInputControl = mTv!!.getCapability(TextInputControl::class.java)
            mouseControl = mTv!!.getCapability(MouseControl::class.java)
            externalInputControl = mTv!!.getCapability(ExternalInputControl::class.java)
            powerControl = mTv!!.getCapability(PowerControl::class.java)
            keyControl = mTv!!.getCapability(KeyControl::class.java)
            webAppLauncher = mTv!!.getCapability(WebAppLauncher::class.java)
            enableButtons()
        }
    }

    open fun disableButtons() {
        if (buttons != null) {
            for (button in buttons!!) {
                button.setOnClickListener(null)
                button.isEnabled = false
            }
        }
    }

   open fun enableButtons() {
        if (buttons != null) {
            for (button in buttons!!) button.isEnabled = true
        }
    }

    fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return false
    }

    fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return false
    }

    fun getTv(): ConnectableDevice? {
        return mTv
    }

    fun getLauncher(): Launcher? {
        return launcher
    }

    fun getMediaPlayer(): MediaPlayer? {
        return mediaPlayer
    }

    fun getMediaControl(): MediaControl? {
        return mediaControl
    }

    fun getVolumeControl(): VolumeControl? {
        return volumeControl
    }

    fun getTVControl(): TVControl? {
        return tvControl
    }

    fun getToastControl(): ToastControl? {
        return toastControl
    }

    fun getTextInputControl(): TextInputControl? {
        return textInputControl
    }

    fun getMouseControl(): MouseControl? {
        return mouseControl
    }

    fun getExternalInputControl(): ExternalInputControl? {
        return externalInputControl
    }

    fun getPowerControl(): PowerControl? {
        return powerControl
    }

    fun getKeyControl(): KeyControl? {
        return keyControl
    }

    fun getWebAppLauncher(): WebAppLauncher? {
        return webAppLauncher
    }

    override fun getContext(): Context? {
        return mContext
    }

    protected fun disableButton(button: Button) {
        button.isEnabled = false
    }
}