package com.android.remotes.lg.features

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.databinding.FragmentWebappBinding
import com.remote.brands.lg.LGBaseFragment
import com.android.remotes.lg.util.TestResponseObject
import com.connectsdk.service.capability.WebAppLauncher
import com.connectsdk.service.capability.listeners.ResponseListener
import com.connectsdk.service.command.ServiceCommandError
import com.connectsdk.service.command.ServiceSubscription
import com.connectsdk.service.sessions.LaunchSession
import com.connectsdk.service.sessions.WebAppSession
import com.connectsdk.service.sessions.WebAppSession.WebAppPinStatusListener
import com.connectsdk.service.sessions.WebAppSessionListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class WebAppFragment : LGBaseFragment {

    private lateinit var binding: FragmentWebappBinding

    companion object {
        const val TAG = "Connect SDK"
        private const val WEBOSID = "webOS TV"
        private const val CASTID = "Chromecast"
        private const val MULTISCREENID = "MultiScreen"

        var isLaunched = false
    }

    var testResponse: TestResponseObject? = null

    var runningAppSession: LaunchSession? = null

    var mWebAppSession: WebAppSession? = null
    var isWebAppPinnedSubscription: ServiceSubscription<WebAppPinStatusListener>? = null
    var webAppId: String? = null

    constructor() : super() {
        testResponse = TestResponseObject()
    }

    constructor(context: Context) : super(context)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebappBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttons = arrayOf(
            binding.launchWebAppButton,
            binding.joinWebAppButton,
            binding.leaveWebAppButton,
            binding.closeWebAppButton,
            binding.sendMessageButton,
            binding.sendJSONButton,
            binding.pinWebAppButton,
            binding.unPinWebAppButton
        )
    }

    override fun enableButtons() {
        super.enableButtons()
        if (getTv()!!.hasCapability(WebAppLauncher.Launch)) {
            binding.launchWebAppButton.setOnClickListener(launchWebApp)
        } else {
            disableButton(binding.launchWebAppButton)
        }
        binding.joinWebAppButton.setEnabled(getTv()!!.hasCapability(WebAppLauncher.Launch))
        binding.joinWebAppButton.setOnClickListener(joinWebApp)
        binding.leaveWebAppButton.setEnabled(getTv()!!.hasCapability(WebAppLauncher.Disconnect))
        binding.leaveWebAppButton.setOnClickListener(leaveWebApp)
        if (getTv()!!.hasCapability(WebAppLauncher.Close)) {
            binding.closeWebAppButton.setOnClickListener(closeWebApp)
        }
        if (getTv()!!.hasCapability(WebAppLauncher.Message_Send)) {
            binding.sendMessageButton.setOnClickListener(sendMessage)
            binding.sendJSONButton.setOnClickListener(sendJson)
        }
        if (getTv()!!.hasCapability(WebAppLauncher.Pin)) {
            binding.pinWebAppButton.setOnClickListener(pinWebApp)
            binding.unPinWebAppButton.setOnClickListener(unPinWebApp)
        }
        binding.responseMessageTextView.setText("")
        if (!isLaunched) {
            disableButton(binding.closeWebAppButton)
            disableButton(binding.leaveWebAppButton)
            disableButton(binding.sendMessageButton)
            disableButton(binding.sendJSONButton)
        } else {
            disableButton(binding.launchWebAppButton)
        }
        if (getTv()!!.getServiceByName(WEBOSID) != null) webAppId =
            "SampleWebApp" else if (getTv()!!.getServiceByName(
                CASTID
            ) != null
        ) webAppId =
            "DDCEDE96" else if (getTv()!!.getServiceByName(MULTISCREENID) != null) webAppId =
            "ConnectSDKSampler"
        if (getTv()!!.hasCapability(WebAppLauncher.Pin)) {
            subscribeIfWebAppIsPinned()
        } else {
            disableButton(binding.pinWebAppButton)
            disableButton(binding.unPinWebAppButton)
        }
    }

    var launchWebApp = View.OnClickListener {
        if (webAppId == null) return@OnClickListener
        binding.launchWebAppButton.setEnabled(false)
        getWebAppLauncher()!!.launchWebApp(webAppId, object : WebAppSession.LaunchListener {
            override fun onError(error: ServiceCommandError) {
                Log.e("LG", "Error connecting to web app | error = $error")
                binding.launchWebAppButton.setEnabled(true)
            }

            override fun onSuccess(webAppSession: WebAppSession) {
                testResponse = TestResponseObject(
                    true,
                    TestResponseObject.SuccessCode,
                    TestResponseObject.Launched_WebAPP
                )
                webAppSession.webAppSessionListener = webAppListener
                isLaunched = true
                if (getTv()!!.hasAnyCapability(
                        WebAppLauncher.Message_Send,
                        WebAppLauncher.Message_Receive,
                        WebAppLauncher.Message_Receive_JSON,
                        WebAppLauncher.Message_Send_JSON
                    )
                ) webAppSession.connect(connectionListener) else connectionListener.onSuccess(
                    webAppSession.launchSession
                )
                mWebAppSession = webAppSession
            }
        })
    }

    var joinWebApp = View.OnClickListener {
        if (webAppId == null) return@OnClickListener
        getWebAppLauncher()!!.joinWebApp(webAppId, object : WebAppSession.LaunchListener {
            override fun onError(error: ServiceCommandError) {
                Log.d("LG", "Could not join")
            }

            override fun onSuccess(webAppSession: WebAppSession) {
                testResponse = TestResponseObject(
                    true,
                    TestResponseObject.SuccessCode,
                    TestResponseObject.Joined_WebAPP
                )
                if (getTv() == null) return
                webAppSession.webAppSessionListener = webAppListener
                mWebAppSession = webAppSession
                binding.sendMessageButton.setEnabled(true)
                binding.launchWebAppButton.setEnabled(false)
                binding.leaveWebAppButton.setEnabled(getTv()!!.hasCapability(WebAppLauncher.Disconnect))
                if (getTv()!!.hasCapabilities(WebAppLauncher.Message_Send_JSON)) binding.sendJSONButton.setEnabled(
                    true
                )
                if (getTv()!!.hasCapabilities(WebAppLauncher.Close)) binding.closeWebAppButton.setEnabled(
                    true
                )
                isLaunched = true
            }
        })
    }

    var leaveWebApp = View.OnClickListener {
        if (mWebAppSession != null) {
            mWebAppSession!!.webAppSessionListener = null
            mWebAppSession!!.disconnectFromWebApp()
            mWebAppSession = null
            binding.launchWebAppButton.setEnabled(true)
            binding.joinWebAppButton.setEnabled(getTv()!!.hasCapability(WebAppLauncher.Join))
            binding.sendMessageButton.setEnabled(false)
            binding.sendJSONButton.setEnabled(false)
            binding.leaveWebAppButton.setEnabled(false)
            binding.closeWebAppButton.setEnabled(false)
            isLaunched = false
        }
    }

    var pinWebApp = View.OnClickListener {
        if (getTv() != null) {
            getWebAppLauncher()!!.pinWebApp(webAppId, object : ResponseListener<Any?> {
                override fun onError(error: ServiceCommandError) {
                    Log.w(TAG, "pin web app failure, " + error.localizedMessage)
                }

                override fun onSuccess(`object`: Any?) {
                    testResponse = TestResponseObject(
                        true,
                        TestResponseObject.SuccessCode,
                        TestResponseObject.Pinned_WebAPP
                    )
                    Log.d(TAG, "pin web app success")
                }
            })
        }
    }

    var unPinWebApp = View.OnClickListener {
        if (webAppId == null) return@OnClickListener
        if (getTv() != null) {
            getWebAppLauncher()!!.unPinWebApp(webAppId, object : ResponseListener<Any?> {
                override fun onError(error: ServiceCommandError) {
                    Log.w(TAG, "unpin web app failture, " + error.localizedMessage)
                }

                override fun onSuccess(`object`: Any?) {
                    testResponse = TestResponseObject(
                        true,
                        TestResponseObject.SuccessCode,
                        TestResponseObject.UnPinned_WebAPP
                    )
                    Log.d(TAG, "unpin web app success")
                }
            })
        }
    }

    fun checkIfWebAppIsPinned() {
        if (webAppId == null) return
        getWebAppLauncher()!!.isWebAppPinned(webAppId, object : WebAppPinStatusListener {
            override fun onError(error: ServiceCommandError) {
                Log.w(TAG, "isWebAppPinned failture, " + error.localizedMessage)
            }

            override fun onSuccess(status: Boolean) {
                updatePinButton(status)
            }
        })
    }

    fun subscribeIfWebAppIsPinned() {
        if (webAppId == null) return
        isWebAppPinnedSubscription = getWebAppLauncher()!!.subscribeIsWebAppPinned(
            webAppId,
            object : WebAppPinStatusListener {
                override fun onError(error: ServiceCommandError) {
                    Log.w(TAG, "isWebAppPinned failure, " + error.localizedMessage)
                }

                override fun onSuccess(status: Boolean) {
                    updatePinButton(status)
                }
            })
    }

    fun updatePinButton(status: Boolean) {
        if (status) {
            binding.pinWebAppButton.setEnabled(false)
            binding.unPinWebAppButton.setEnabled(true)
        } else {
            binding.pinWebAppButton.setEnabled(true)
            binding.unPinWebAppButton.setEnabled(false)
        }
    }

    var webAppListener: WebAppSessionListener = object : WebAppSessionListener {
        override fun onReceiveMessage(webAppSession: WebAppSession, message: Any) {
            Log.d(TAG, "Message received from app | $message")
            if (message.javaClass == String::class.java) {
                binding.responseMessageTextView.append(message as String)
                binding.responseMessageTextView.append("\n")
            } else if (message.javaClass == JSONObject::class.java) {
                binding.responseMessageTextView.append((message as JSONObject).toString())
                binding.responseMessageTextView.append("\n")
            }
        }

        override fun onWebAppSessionDisconnect(webAppSession: WebAppSession) {
            Log.d("LG", "Device was disconnected")
            if (webAppSession !== mWebAppSession) {
                webAppSession.webAppSessionListener = null
                return
            }
            binding.launchWebAppButton.setEnabled(true)
            if (getTv() != null) binding.joinWebAppButton.setEnabled(
                getTv()!!.hasCapability(
                    WebAppLauncher.Join
                )
            )
            binding.sendMessageButton.setEnabled(false)
            binding.sendJSONButton.setEnabled(false)
            binding.leaveWebAppButton.setEnabled(false)
            binding.closeWebAppButton.setEnabled(false)
            mWebAppSession!!.webAppSessionListener = null
            mWebAppSession = null
            isLaunched = false
        }
    }

    var connectionListener: ResponseListener<Any?> = object : ResponseListener<Any?> {
        override fun onSuccess(response: Any?) {
            if (getTv() == null) return
            if (getTv()!!.hasCapability(WebAppLauncher.Message_Send_JSON)) binding.sendJSONButton.setEnabled(
                true
            )
            if (getTv()!!.hasCapability(WebAppLauncher.Message_Send)) binding.sendMessageButton.setEnabled(
                true
            )
            binding.leaveWebAppButton.setEnabled(getTv()!!.hasCapability(WebAppLauncher.Disconnect))
            binding.closeWebAppButton.setEnabled(true)
            binding.launchWebAppButton.setEnabled(false)
            isLaunched = true
        }

        override fun onError(error: ServiceCommandError) {
            binding.sendJSONButton.setEnabled(false)
            binding.sendMessageButton.setEnabled(false)
            binding.closeWebAppButton.setEnabled(false)
            binding.launchWebAppButton.setEnabled(true)
            isLaunched = false
            if (mWebAppSession != null) {
                mWebAppSession!!.webAppSessionListener = null
                mWebAppSession!!.close(null)
            }
        }
    }

    var sendMessage = View.OnClickListener {
        val message = "This is an Android test message."
        mWebAppSession!!.sendMessage(message, object : ResponseListener<Any> {
            override fun onSuccess(response: Any) {
                testResponse = TestResponseObject(
                    true,
                    TestResponseObject.SuccessCode,
                    TestResponseObject.Sent_Message
                )
                Log.d(TAG, "Sent message : $response")
            }

            override fun onError(error: ServiceCommandError) {
                Log.e(TAG, "Error sending message : $error")
            }
        })
    }

    var sendJson = View.OnClickListener {
        var message: JSONObject? = null
        message = try {
            object : JSONObject() {
                init {
                    put("type", "message")
                    put("contents", "This is a test message")
                    put("params", object : JSONObject() {
                        init {
                            put(
                                "someParam1",
                                "The content & format of this JSON block can be anything"
                            )
                            put("someParam2", "The only limit ... is yourself")
                        }
                    })
                    put("anArray", object : JSONArray() {
                        init {
                            put("Just")
                            put("to")
                            put("show")
                            put("we")
                            put("can")
                            put("send")
                            put("arrays!")
                        }
                    })
                }
            }
        } catch (e: JSONException) {
            return@OnClickListener
        }
        mWebAppSession!!.sendMessage(message, object : ResponseListener<Any> {
            override fun onSuccess(response: Any) {
                testResponse = TestResponseObject(
                    true,
                    TestResponseObject.SuccessCode,
                    TestResponseObject.Sent_JSON
                )
                Log.d(TAG, "Sent message : $response")
            }

            override fun onError(error: ServiceCommandError) {
                Log.e(TAG, "Error sending message : $error")
            }
        })
    }

    var closeWebApp = View.OnClickListener {
        binding.responseMessageTextView.setText("")
        binding.closeWebAppButton.setEnabled(false)
        binding.sendMessageButton.setEnabled(false)
        binding.sendJSONButton.setEnabled(false)
        binding.leaveWebAppButton.setEnabled(false)
        isLaunched = false
        mWebAppSession!!.webAppSessionListener = null
        mWebAppSession!!.close(object : ResponseListener<Any?> {
            override fun onSuccess(response: Any?) {
                testResponse = TestResponseObject(
                    true,
                    TestResponseObject.SuccessCode,
                    TestResponseObject.Close_WebAPP
                )
                binding.launchWebAppButton.setEnabled(true)
            }

            override fun onError(error: ServiceCommandError) {
                Log.e(TAG, "Error closing web app | error = $error")
                binding.launchWebAppButton.setEnabled(true)
            }
        })
    }

    override fun disableButtons() {
        super.disableButtons()
        isLaunched = false
        binding.responseMessageTextView.text = ""
        webAppId = null
    }

    fun setRunningAppInfo(session: LaunchSession) {
        runningAppSession = session
    }

}