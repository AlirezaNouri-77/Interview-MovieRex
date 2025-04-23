package com.shermanrex.movierex.data.util

import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import com.shermanrex.movierex.data.model.NetWorkError
import com.shermanrex.movierex.data.model.Result

inline fun <reified T> safeCall(action: () -> Response<T>): Result<T, NetWorkError> {
  return try {
    var response = action()
    if (response.isSuccessful) {
      responseMapper(response)
    } else Result.Failure<NetWorkError>(NetWorkError.RESPONSE_NOT_SUCCESSFUL)
  } catch (e: SocketTimeoutException) {
    e.printStackTrace()
    Result.Failure<NetWorkError>(NetWorkError.TIME_OUT)
  } catch (e: HttpException) {
    e.printStackTrace()
    Result.Failure<NetWorkError>(NetWorkError.SERVER_CONNECTION)
  } catch (e: IOException) {
    e.printStackTrace()
    Result.Failure<NetWorkError>(NetWorkError.INTERNET_CONNECTION)
  } catch (e: SerializationException) {
    e.printStackTrace()
    Result.Failure<NetWorkError>(NetWorkError.SERIALIZATION)
  } catch (e: Exception) {
    e.printStackTrace()
    Result.Failure<NetWorkError>(NetWorkError.UNKNOWN)
  }
}

inline fun <reified T> responseMapper(response: Response<T>): Result<T, NetWorkError> {
  return when (response.code()) {
    in 200..299 -> Result.Success<T>(response.body() as T)
    404 -> Result.Failure(NetWorkError.NOT_FOUND_404)
    500 -> Result.Failure(NetWorkError.SERVER_CONNECTION)
    else -> Result.Failure(NetWorkError.UNKNOWN)
  }
}