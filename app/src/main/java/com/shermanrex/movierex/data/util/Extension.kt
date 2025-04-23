package com.shermanrex.movierex.data.util

import android.content.Context
import com.shermanrex.interview_movierex.R
import com.shermanrex.movierex.data.model.NetWorkError

fun NetWorkError.toReadableMessage(context: Context): String {
    return when(this){
        NetWorkError.TIME_OUT -> context.getString(R.string.time_out)
        NetWorkError.INTERNET_CONNECTION -> context.getString(R.string.internet_connection)
        NetWorkError.SERIALIZATION -> context.getString(R.string.unknown)
        NetWorkError.UNKNOWN -> context.getString(R.string.unknown)
        NetWorkError.SERVER_CONNECTION -> context.getString(R.string.server_connection)
        NetWorkError.RESPONSE_NOT_SUCCESSFUL -> context.getString(R.string.unknown)
        NetWorkError.NOT_FOUND_404 -> context.getString(R.string.not_found)
    }
}