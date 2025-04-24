package com.shermanrex.movierex.util

import com.shermanrex.movierex.domain.util.NetworkConnectivityImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update

class NetworkConnectivityFake : NetworkConnectivityImpl {

    var isConnected = MutableStateFlow(false)

    override var networkState: Flow<Boolean> = flow {
        emit(isConnected.value)
    }

    fun setConnectivityState(boolean: Boolean) {
        isConnected.update { boolean }
    }

}