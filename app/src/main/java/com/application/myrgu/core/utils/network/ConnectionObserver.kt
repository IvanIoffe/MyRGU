package com.application.myrgu.core.utils.network

import kotlinx.coroutines.flow.Flow

interface ConnectionObserver {
    fun observe(): Flow<NetworkState>
}