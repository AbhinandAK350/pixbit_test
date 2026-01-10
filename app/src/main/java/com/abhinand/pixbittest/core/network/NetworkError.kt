package com.abhinand.pixbittest.core.network

sealed class NetworkError {
    data object Timeout : NetworkError()
    data object NoInternet : NetworkError()
    data object Server : NetworkError()
    data object Unknown : NetworkError()
}
