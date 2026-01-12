package com.abhinand.pixbittest.core.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.toNetworkError(): NetworkError =
    when (this) {
        is UnknownHostException -> NetworkError.NoInternet

        is SocketTimeoutException -> NetworkError.Timeout

        is ConnectException -> NetworkError.Server

        is HttpException -> NetworkError.Server

        else -> NetworkError.Unknown
    }

fun NetworkError.toUserMessage(): String =
    when (this) {
        NetworkError.Timeout ->
            "Connection timed out. Please try again."

        NetworkError.NoInternet ->
            "No internet connection. Check your network and try again."

        NetworkError.Server ->
            "Server error. Please try again later."

        NetworkError.Unknown ->
            "Something went wrong. Please try again."
    }

val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

fun HttpException.parseErrorBody(): ApiErrorResponse? {
    return runCatching {
        val errorJson = response()?.errorBody()?.string() ?: return null
        json.decodeFromString<ApiErrorResponse>(errorJson)
    }.getOrNull()
}

@Serializable
data class ApiErrorResponse(
    val error: String?,
)


