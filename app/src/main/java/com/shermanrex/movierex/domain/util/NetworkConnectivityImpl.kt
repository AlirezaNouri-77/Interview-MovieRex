package com.shermanrex.movierex.domain.util

import kotlinx.coroutines.flow.Flow

interface NetworkConnectivityImpl {
  var networkState: Flow<Boolean>
}