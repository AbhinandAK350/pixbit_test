package com.abhinand.pixbittest.register.data.repository

import android.util.Log
import com.abhinand.pixbittest.core.network.NetworkResource
import com.abhinand.pixbittest.core.network.NetworkUtils
import com.abhinand.pixbittest.core.network.parseErrorBody
import com.abhinand.pixbittest.core.network.toNetworkError
import com.abhinand.pixbittest.core.network.toUserMessage
import com.abhinand.pixbittest.register.data.mapper.toRegister
import com.abhinand.pixbittest.register.data.remote.api.RegisterApi
import com.abhinand.pixbittest.register.data.remote.dto.RegisterRequest
import com.abhinand.pixbittest.register.domain.model.Register
import com.abhinand.pixbittest.register.domain.repository.RegisterRepository
import retrofit2.HttpException
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val api: RegisterApi,
    private val networkUtils: NetworkUtils
) : RegisterRepository {
    override suspend fun register(registerRequest: RegisterRequest): NetworkResource<Register> {
        if (!networkUtils.isNetworkAvailable()) {
            return NetworkResource.Error("No internet connection")
        }
        return try {
            val response = api.register(registerRequest)
            if (response.access_token.isNullOrBlank()) {
                NetworkResource.Error("Invalid server response")
            } else {
                NetworkResource.Success(response.toRegister())
            }
        } catch (e: Exception) {
            Log.e("RegisterRepositoryImpl", "register: ", e)
            val errorMessage = when (e) {
                is HttpException -> {
                    e.parseErrorBody()
                        ?: e.toNetworkError().toUserMessage()
                }

                else -> e.toNetworkError().toUserMessage()
            }

            NetworkResource.Error(errorMessage)
        }
    }
}
