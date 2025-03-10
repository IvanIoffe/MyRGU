package com.application.myrgu.core.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class NetworkConnectionObserver @Inject constructor(
    private val context: Context,
) : ConnectionObserver {

    private val connectivityManager =
        context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager

    override fun observe(): Flow<NetworkState> {
        return callbackFlow {
            val networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    trySend(NetworkState.AVAILABLE)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    trySend(NetworkState.LOST)
                }
            }

            if (!isNetworkAvailable(context)) {
                trySend(NetworkState.UNAVAILABLE)
            }

            registerDefaultNetworkCallback(networkCallback)

            awaitClose {
                unregisterNetworkCallback(networkCallback)
            }
        }
    }

    private fun registerDefaultNetworkCallback(networkCallback: ConnectivityManager.NetworkCallback) {
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    private fun unregisterNetworkCallback(networkCallback: ConnectivityManager.NetworkCallback) {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}