package com.shermanrex.movierex.domain

import kotlinx.coroutines.flow.Flow

interface NetworkConnectivityImpl {
  var networkState: Flow<Boolean>
}