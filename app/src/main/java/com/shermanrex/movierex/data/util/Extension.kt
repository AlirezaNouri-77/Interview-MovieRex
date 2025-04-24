package com.shermanrex.movierex.data.util

import android.content.Context
import com.shermanrex.interview_movierex.R
import com.shermanrex.movierex.domain.model.NetworkError

fun NetworkError.toReadableMessage(context: Context): String {
    return when(this){
        NetworkError.TIME_OUT -> context.getString(R.string.time_out)
        NetworkError.INTERNET_CONNECTION -> context.getString(R.string.internet_connection)
        NetworkError.SERIALIZATION -> context.getString(R.string.unknown)
        NetworkError.UNKNOWN -> context.getString(R.string.unknown)
        NetworkError.SERVER_CONNECTION -> context.getString(R.string.server_connection)
        NetworkError.RESPONSE_NOT_SUCCESSFUL -> context.getString(R.string.unknown)
        NetworkError.NOT_FOUND_404 -> context.getString(R.string.not_found)
    }
}