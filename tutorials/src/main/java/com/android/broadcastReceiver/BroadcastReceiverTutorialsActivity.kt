package com.android.broadcastReceiver

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.broadcastReceiver.custom.MyBroadcastReceiver
import com.android.broadcastReceiver.network.NetworkBroadcastReceiver
import com.android.databinding.ActivityBroadcastReceiverTutorialsBinding


class BroadcastReceiverTutorialsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBroadcastReceiverTutorialsBinding
    private lateinit var networkReceiver: NetworkBroadcastReceiver
    private lateinit var customReceiver: MyBroadcastReceiver

    companion object {
        const val TAG = "BroadcastReceiverTutorialsActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: $TAG")
        binding = ActivityBroadcastReceiverTutorialsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.networkReceiver = NetworkBroadcastReceiver()
        this.customReceiver = MyBroadcastReceiver()

        customReceiver.liveData.observe(this) {
            binding.tvReceiver.text = it
        }

        binding.buttonSend.setOnClickListener {
            if (binding.editText.text.trim().toString().isNotEmpty()) {
                val intent = Intent(MyBroadcastReceiver.CUSTOM_ACTION)
                intent.putExtra(
                    MyBroadcastReceiver.DATA_KEY,
                    binding.editText.text.trim().toString()
                )
                sendBroadcast(intent)
            } else {
                Toast.makeText(this, "Typing data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        @Suppress("DEPRECATION")
        val intent = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkReceiver, intent)
        val customIntentFilter = IntentFilter(MyBroadcastReceiver.CUSTOM_ACTION)
        registerReceiver(customReceiver, customIntentFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(networkReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(customReceiver)
        finish()
    }
}