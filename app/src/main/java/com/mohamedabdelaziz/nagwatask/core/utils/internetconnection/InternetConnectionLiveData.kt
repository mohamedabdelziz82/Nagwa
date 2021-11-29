package com.mohamedabdelaziz.nagwatask.core.utils.internetconnection

import androidx.lifecycle.LiveData
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.NetworkInfo

class InternetConnectionLiveData(private var context: Context) : LiveData<Boolean?>() {
    override fun onActive() {
        super.onActive()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(networkReceiver, filter)
    }

    override fun onInactive() {
        super.onInactive()
        context.unregisterReceiver(networkReceiver)
    }

    private val networkReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val connManager = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connManager.activeNetworkInfo
            val isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting
            if (isConnected) {
                if (isNetworkAvailable(context)) {
                    postValue(true)
                } else {
                    postValue(false)
                }
            } else {
                postValue(false)
            }
        }
    }

    fun isNetworkAvailable(context: Context): Boolean {

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }
}