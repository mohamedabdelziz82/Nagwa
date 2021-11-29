package com.mohamedabdelaziz.nagwatask.core.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mohamedabdelaziz.nagwatask.core.utils.internetconnection.InternetConnectionLiveData

abstract class BaseActivity : AppCompatActivity() {
   lateinit var connectionLiveData: InternetConnectionLiveData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectionLiveData = InternetConnectionLiveData(this)
        observeNetworkState()
    }

    private fun observeNetworkState() {
        connectionLiveData!!.observe(this, {
            if (it!!) {
                onConnectionSuccess()
            } else {
                onConnectionFailed()
            }
        })
    }

    protected abstract fun onConnectionSuccess()
    protected abstract fun onConnectionFailed()
}