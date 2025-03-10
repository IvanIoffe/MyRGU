package com.application.myrgu.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.myrgu.core.utils.network.ConnectionObserver
import com.application.myrgu.core.utils.network.NetworkState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    connectionObserver: ConnectionObserver,
) : ViewModel() {

    val networkState = connectionObserver.observe()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = NetworkState.INITIAL
        )
}