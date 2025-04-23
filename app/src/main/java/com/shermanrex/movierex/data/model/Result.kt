package com.shermanrex.movierex.data.model

sealed class Result<out T, out E> {
    data class Success<T>(var data: T) : Result<T, Nothing>()
    data class Failure<E>(var error: NetWorkError) : Result<Nothing, E>()
}
