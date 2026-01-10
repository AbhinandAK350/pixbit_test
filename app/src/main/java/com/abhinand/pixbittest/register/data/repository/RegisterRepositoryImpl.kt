package com.abhinand.pixbittest.register.data.repository

import com.abhinand.pixbittest.core.network.NetworkResource
import com.abhinand.pixbittest.core.network.NetworkUtils
import com.abhinand.pixbittest.register.data.remote.api.RegisterApi
import com.abhinand.pixbittest.register.data.remote.dto.RegisterRequest
import com.abhinand.pixbittest.register.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val api: RegisterApi,
    private val networkUtils: NetworkUtils
) : RegisterRepository {
    override suspend fun register(registerRequest: RegisterRequest): NetworkResource<Unit> {
        if (!networkUtils.isNetworkAvailable()) {
            return NetworkResource.Error("No internet connection")
        }
        return try {
            val response = api.register(registerRequest)
            if (response.success) {
                NetworkResource.Success(Unit)
            } else {
                NetworkResource.Error("Registration failed")
            }
        } catch (e: Exception) {
            NetworkResource.Error(e.message ?: "An unknown error occurred")
        }
    }
}