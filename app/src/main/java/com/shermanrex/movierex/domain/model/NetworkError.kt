package com.shermanrex.movierex.domain.model

enum class NetworkError {
    TIME_OUT,
    INTERNET_CONNECTION,
    SERIALIZATION,
    UNKNOWN,
    SERVER_CONNECTION,
    RESPONSE_NOT_SUCCESSFUL,
    NOT_FOUND_404,
}