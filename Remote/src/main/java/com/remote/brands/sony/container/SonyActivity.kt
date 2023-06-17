package com.remote.brands.sony.container

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.AsyncTask
import android.text.format.Formatter
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.remote.R
import com.remote.commons.base.BaseActivity
import com.remote.databinding.ActivitySonyBinding
import java.lang.ref.WeakReference
import java.net.InetAddress


class SonyActivity : BaseActivity<ActivitySonyBinding, SonyViewModel>() {

//    private lateinit var networkSniffTask: NetworkSniffTask

    override fun createViewBinding(): ActivitySonyBinding {
        return ActivitySonyBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): SonyViewModel {
        return ViewModelProvider(this)[SonyViewModel::class.java]
    }

    override fun initializeViews() {
        // init navigation component
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as NavHostFragment
        val navController = navHostFragment.navController

//        this.networkSniffTask = NetworkSniffTask(this)
    }

    override fun initializeEvents() {

    }

    private fun getAllIpAddresses() {

    }

//    class NetworkSniffTask(context: Context) : AsyncTask<Void, Void, Void>() {
//
//        private var mContextRef: WeakReference<Context>? = null
//
//        init {
//            mContextRef = WeakReference(context)
//        }
//
//        override fun doInBackground(vararg voids: Void?): Void? {
//            try {
//                val context = mContextRef!!.get()
//
//                if (context != null) {
//                    val cm =
//                        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//                    val activeNetwork = cm.activeNetworkInfo
//                    val wm =
//                        context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
//                    val connectionInfo = wm.connectionInfo
//                    val ipAddress = connectionInfo.ipAddress
//                    val ipString: String = Formatter.formatIpAddress(ipAddress)
//                    Log.d(
//                        NetworkSniffTask::class.simpleName,
//                        "activeNetwork: " + activeNetwork.toString()
//                    )
//                    Log.d(NetworkSniffTask::class.simpleName, "ipString: $ipString")
//                    val prefix = ipString.substring(0, ipString.lastIndexOf(".") + 1)
//                    Log.d(NetworkSniffTask::class.simpleName, "prefix: $prefix")
//                    for (i in 0..254) {
//                        val testIp = prefix + i.toString()
//                        val address = InetAddress.getByName(testIp)
//                        val reachable = address.isReachable(1000)
//                        val hostName = address.canonicalHostName
//                        if (reachable) Log.i(
//                            NetworkSniffTask::class.simpleName,
//                            "Host: $hostName($testIp) is reachable!"
//                        )
//                    }
//                }
//            } catch (throwable: Throwable) {
//                Log.e(NetworkSniffTask::class.simpleName, "Well that's not good.", throwable)
//            }
//            return null
//        }
//    }
}