package com.example.broadcastreceiverdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var batteryProgressBar: ProgressBar
    private lateinit var batteryHealthTV : TextView
    private lateinit var networkImageView: ImageView


    private var batteryBroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                val percentage = (level * 100 / scale.toFloat()).toInt()

                Log.d("MainActivity", "level : $level and scale : $scale")

                batteryProgressBar.progress = percentage
                batteryHealthTV.text = context?.getString(R.string.bat_perc_msg, percentage)
            }
        }
    }

    private var networkBroadcastReceiver = object : ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            this@MainActivity.runOnUiThread {
                networkImageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.ic_signal_wifi_4_bar_black_18dp
                    )
                )
            }
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            this@MainActivity.runOnUiThread {
                networkImageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.ic_signal_wifi_off_black_18dp
                    )
                )
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        batteryProgressBar = findViewById(R.id.battery_pb)
        batteryHealthTV = findViewById(R.id.batteryhealth_tv)
        networkImageView = findViewById(R.id.networkImageView)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(batteryBroadcastReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

        val connectivityManager = getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_WIFI).build()
        connectivityManager.registerNetworkCallback(networkRequest, networkBroadcastReceiver)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(batteryBroadcastReceiver)
        val connectivityManager = getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(networkBroadcastReceiver)
    }
}