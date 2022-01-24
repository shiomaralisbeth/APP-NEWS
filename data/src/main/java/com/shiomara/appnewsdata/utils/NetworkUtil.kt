package com.shiomara.appnewsdata.utils

import android.content.Context
import android.net.ConnectivityManager

interface NetworkUtil {
    fun hasNetworkAccess(): Boolean
}

class NetworkUtilImpl(private val context: Context) : NetworkUtil {
    override fun hasNetworkAccess(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectivityManager.activeNetworkInfo
        return info != null && info.isConnected
    }
}