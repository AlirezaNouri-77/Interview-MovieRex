package com.shermanrex.movierex.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import com.shermanrex.movierex.domain.util.NetworkConnectivityImpl
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NetworkConnectivity(
  context: Context,
) : NetworkConnectivityImpl {

  private var connectivityManager: ConnectivityManager = context.getSystemService(ConnectivityManager::class.java)

  override var networkState: Flow<Boolean> = callbackFlow {
    var listener = object : ConnectivityManager.NetworkCallback() {

      override fun onAvailable(network: Network) {
        super.onAvailable(network)
        trySend(true)
      }

      override fun onLost(network: Network) {
        super.onLost(network)
        trySend(false)
      }

      override fun onUnavailable() {
        super.onUnavailable()
        trySend(false)
      }

      override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities)
        var isConnected = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        trySend(isConnected)
      }
    }

    var isConnected = initialNetworkState()
    trySend(isConnected)

    connectivityManager.registerDefaultNetworkCallback(listener)
    awaitClose {
      connectivityManager.unregisterNetworkCallback(listener)
    }
  }

  private fun initialNetworkState(): Boolean {
    var initial = connectivityManager.activeNetwork
    val network = connectivityManager.getNetworkCapabilities(initial)
    var isConnected = network?.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) == true
    return isConnected
  }

}