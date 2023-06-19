package com.remote.brands.lg.features

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import com.connectsdk.core.AppInfo
import com.connectsdk.service.capability.Launcher
import com.connectsdk.service.capability.Launcher.*
import com.connectsdk.service.capability.ToastControl
import com.connectsdk.service.capability.listeners.ResponseListener
import com.connectsdk.service.command.ServiceCommandError
import com.connectsdk.service.command.ServiceSubscription
import com.connectsdk.service.sessions.LaunchSession
import com.remote.R
import com.remote.brands.lg.LGBaseFragment
import com.remote.brands.lg.util.TestResponseObject
import com.remote.brands.lg.widget.AppAdapter
import com.remote.databinding.FragmentAppsBinding

class AppsFragment : LGBaseFragment {

    private lateinit var binding: FragmentAppsBinding

    private var adapter: AppAdapter? = null
    var runningAppSession: LaunchSession? = null
    var appStoreSession: LaunchSession? = null
    var myAppSession: LaunchSession? = null
    var testResponse: TestResponseObject? = null

    var runningAppSubs: ServiceSubscription<AppInfoListener>? = null

    constructor() : super() {
        testResponse = TestResponseObject()
    }

    constructor(context: Context) : super(context) {
        testResponse = TestResponseObject()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAppsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AppAdapter(requireContext(), R.layout.app_item)
        binding.appListView.adapter = adapter

        buttons = arrayOf(
            binding.browserButton,
            binding.deepLinkButton,
            binding.youtubeButton,
            binding.toastButton,
            binding.myApp,
            binding.appStoreButton
        )
    }

    override fun enableButtons() {
        super.enableButtons()

        if (getTv()!!.hasCapability(Launcher.Browser)
            || getTv()!!.hasCapability(Launcher.Browser_Params)
        ) {
            binding.browserButton.setOnClickListener(View.OnClickListener {
                if (binding.browserButton.isSelected) {
                    binding.browserButton.isSelected = false
                    if (runningAppSession != null) {
                        runningAppSession!!.close(null)
                    }
                } else {
                    binding.browserButton.isSelected = true
                    getLauncher()!!.launchBrowser(
                        "http://connectsdk.com/",
                        object : AppLaunchListener {
                            override fun onSuccess(session: LaunchSession) {
                                setRunningAppInfo(session)
                                testResponse = TestResponseObject(
                                    true,
                                    TestResponseObject.SuccessCode,
                                    TestResponseObject.Launched_Browser
                                )
                            }

                            override fun onError(error: ServiceCommandError) {}
                        })
                }
            })
        } else {
            disableButton(binding.browserButton)
        }

        if (getTv()!!.hasCapability(ToastControl.Show_Toast)) {
            binding.toastButton.setOnClickListener(View.OnClickListener {
                getToastControl()!!.showToast("Yeah, toast!", getToastIconData(), "png", null)
                testResponse = TestResponseObject(
                    true,
                    TestResponseObject.SuccessCode,
                    TestResponseObject.Show_Toast
                )
            })
        } else {
            disableButton(binding.toastButton)
        }

        if (getTv()!!.hasCapability(Launcher.Netflix)
            || getTv()!!.hasCapability(Launcher.Netflix_Params)
        ) {
            binding.deepLinkButton.setOnClickListener(View.OnClickListener {
                if (binding.deepLinkButton.isSelected()) {
                    binding.deepLinkButton.setSelected(false)
                    if (runningAppSession != null) {
                        runningAppSession!!.close(null)
                    }
                } else {
                    binding.deepLinkButton.setSelected(true)
                    getLauncher()!!.launchNetflix("70217913", object : AppLaunchListener {
                        override fun onSuccess(session: LaunchSession) {
                            setRunningAppInfo(session)
                            testResponse = TestResponseObject(
                                true,
                                TestResponseObject.SuccessCode,
                                TestResponseObject.Launched_Netflix
                            )
                        }

                        override fun onError(error: ServiceCommandError) {}
                    })
                }
            })
        } else {
            disableButton(binding.deepLinkButton)
        }

        if (getTv()!!.hasCapability(Launcher.YouTube) ||
            getTv()!!.hasCapability(Launcher.YouTube_Params)
        ) {
            binding.youtubeButton.setOnClickListener(View.OnClickListener {
                if (binding.youtubeButton.isSelected) {
                    binding.youtubeButton.isSelected = false
                    if (runningAppSession != null) {
                        runningAppSession!!.close(null)
                    }
                } else {
                    binding.youtubeButton.isSelected = true
                    getLauncher()!!.launchYouTube("eRsGyueVLvQ", object : AppLaunchListener {
                        override fun onSuccess(session: LaunchSession) {
                            setRunningAppInfo(session)
                            testResponse = TestResponseObject(
                                true,
                                TestResponseObject.SuccessCode,
                                TestResponseObject.Launched_Youtube
                            )
                        }

                        override fun onError(error: ServiceCommandError) {}
                    })
                }
            })
        } else {
            disableButton(binding.youtubeButton)
        }

        binding.browserButton.isSelected = false
        binding.deepLinkButton.isSelected = false
        binding.youtubeButton.isSelected = false

        if (getTv()!!.hasCapability(Launcher.RunningApp_Subscribe)) {
            runningAppSubs = getLauncher()!!.subscribeRunningApp(object : AppInfoListener {
                override fun onSuccess(appInfo: AppInfo) {
                    adapter!!.setRunningAppId(appInfo.id)
                    adapter!!.notifyDataSetChanged()
                    val position = adapter!!.getPosition(appInfo)
                    binding.appListView.setSelection(position)
                }

                override fun onError(error: ServiceCommandError) {}
            })
        }

        if (getTv()!!.hasCapability(Launcher.Application_List)) {
            getLauncher()!!.getAppList(object : AppListListener {
                override fun onSuccess(appList: List<AppInfo?>) {
                    adapter!!.clear()
                    for (i in appList.indices) {
                        val app = appList[i]
                        adapter!!.add(app)
                    }
                    adapter!!.sort()
                }

                override fun onError(error: ServiceCommandError) {}
            })
        }

        binding.appListView.onItemClickListener = OnItemClickListener { arg0, arg1, arg2, arg3 ->
            if (runningAppSession != null) {
                runningAppSession!!.close(null)
            }
            val appInfo = arg0.getItemAtPosition(arg2) as AppInfo
            getLauncher()!!.launchAppWithInfo(appInfo, null, object : AppLaunchListener {
                override fun onSuccess(session: LaunchSession) {
                    setRunningAppInfo(session)
                }

                override fun onError(error: ServiceCommandError) {}
            })
        }

        if (getTv()!!.hasCapability(Launcher.Browser)) {
            if (getTv()!!.hasCapability(Launcher.Browser_Params)) {
                binding.browserButton.text = "Open Google"
            } else {
                binding.browserButton.text = "Open Browser"
            }
        }

        binding.myApp.setEnabled(getTv()!!.hasCapability("Launcher.Levak"))
        binding.myApp.setOnClickListener(myAppLaunch)

        binding.appStoreButton.isEnabled = getTv()!!.hasCapability(Launcher.AppStore_Params)
        binding.appStoreButton.setOnClickListener(launchAppStore)

    }

    var myAppLaunch = View.OnClickListener {
        if (myAppSession != null) {
            myAppSession!!.close(null)
            myAppSession = null
            binding.myApp.setSelected(false)
        } else {
            getLauncher()!!.launchApp("Levak", object : AppLaunchListener {
                override fun onError(error: ServiceCommandError) {
                    Log.d("LG", "My app failed: $error")
                }

                override fun onSuccess(`object`: LaunchSession) {
                    myAppSession = `object`
                    binding.myApp.setSelected(true)
                }
            })
        }
    }

    var launchAppStore = View.OnClickListener {
        if (appStoreSession != null) {
            appStoreSession!!.close(object : ResponseListener<Any?> {
                override fun onError(error: ServiceCommandError) {
                    Log.d("LG", "App Store close error: $error")
                }

                override fun onSuccess(`object`: Any?) {
                    Log.d("LG", "AppStore close success")
                }
            })
            appStoreSession = null
            binding.appStoreButton.setSelected(false)
        } else {
            var appId: String? = null
            if (getTv()!!.getServiceByName("Netcast TV") != null) appId =
                "125071" else if (getTv()!!.getServiceByName("webOS TV") != null) appId =
                "redbox" else if (getTv()!!.getServiceByName("Roku") != null) appId = "13535"
            getLauncher()!!.launchAppStore(appId, object : AppLaunchListener {
                override fun onError(error: ServiceCommandError) {
                    Log.d("LG", "App Store failed: $error")
                }

                override fun onSuccess(`object`: LaunchSession) {
                    Log.d("LG", "App Store launched!")
                    appStoreSession = `object`
                    binding.appStoreButton.setSelected(true)
                }
            })
        }
    }

    override fun disableButtons() {
        if (runningAppSubs != null) runningAppSubs!!.unsubscribe()
        adapter!!.clear()
        super.disableButtons()
    }

    fun setRunningAppInfo(session: LaunchSession) {
        runningAppSession = session
    }

    protected fun getToastIconData(): String? {
        return mContext!!.getString(R.string.toast_icon_data)
    }
}