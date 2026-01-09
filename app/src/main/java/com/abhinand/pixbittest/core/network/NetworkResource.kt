package com.abhinand.pixbittest.core.network

sealed interface NetworkResource<out T> {
    data class Success<T>(val data: T?) : NetworkResource<T>
    data class Error(val message: String?, val code: Int? = null) : NetworkResource<Nothing>
}