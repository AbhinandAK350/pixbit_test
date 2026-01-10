package com.abhinand.pixbittest.login.data.repository

import android.util.Log
import com.abhinand.pixbittest.core.data.DataStore
import com.abhinand.pixbittest.core.network.NetworkResource
import com.abhinand.pixbittest.core.network.NetworkUtils
import com.abhinand.pixbittest.core.network.toNetworkError
import com.abhinand.pixbittest.core.network.toUserMessage
import com.abhinand.pixbittest.login.data.mapper.toDomain
import com.abhinand.pixbittest.login.data.remote.LoginApi
import com.abhinand.pixbittest.login.data.remote.dto.LoginRequest
import com.abhinand.pixbittest.login.domain.model.Login
import com.abhinand.pixbittest.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: LoginApi,
    private val networkUtils: NetworkUtils,
    private val dataStore: DataStore
) : LoginRepository {
    override suspend fun login(loginRequest: LoginRequest): NetworkResource<Login> {
        if (!networkUtils.isNetworkAvailable()) {
            return NetworkResource.Error("No internet connection")
        }
        return try {
            val response = api.login(loginRequest)
            if (response.success) {
                NetworkResource.Success(response.toDomain())
            } else {
                NetworkResource.Error("Registration failed")
            }
        } catch (e: Exception) {
            Log.e("RegisterRepositoryImpl", "register: ", e)
            val error = e.toNetworkError()
            NetworkResource.Error(error.toUserMessage())
        }
    }

    override suspend fun saveToken(token: String) {
        dataStore.saveToken(token)
    }

    override fun isLoggedIn() = dataStore.getToken().map {
        !it.isNullOrEmpty()
    }

}