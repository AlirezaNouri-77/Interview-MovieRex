package com.shermanrex.movierex.data.util

import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import com.shermanrex.movierex.domain.model.NetworkError
import com.shermanrex.movierex.domain.model.Result

inline fun <reified T> safeCall(action: () -> Response<T>): Result<T, NetworkError> {
  return try {
    var response = action()
    if (response.isSuccessful) {
      responseMapper(response)
    } else Result.Failure<NetworkError>(NetworkError.RESPONSE_NOT_SUCCESSFUL)
  } catch (e: SocketTimeoutException) {
    e.printStackTrace()
    Result.Failure<NetworkError>(NetworkError.TIME_OUT)
  } catch (e: HttpException) {
    e.printStackTrace()
    Result.Failure<NetworkError>(NetworkError.SERVER_CONNECTION)
  } catch (e: IOException) {
    e.printStackTrace()
    Result.Failure<NetworkError>(NetworkError.INTERNET_CONNECTION)
  } catch (e: SerializationException) {
    e.printStackTrace()
    Result.Failure<NetworkError>(NetworkError.SERIALIZATION)
  } catch (e: Exception) {
    e.printStackTrace()
    Result.Failure<NetworkError>(NetworkError.UNKNOWN)
  }
}

inline fun <reified T> responseMapper(response: Response<T>): Result<T, NetworkError> {
  return when (response.code()) {
    in 200..299 -> Result.Success<T>(response.body() as T)
    404 -> Result.Failure(NetworkError.NOT_FOUND_404)
    500 -> Result.Failure(NetworkError.SERVER_CONNECTION)
    else -> Result.Failure(NetworkError.UNKNOWN)
  }
}