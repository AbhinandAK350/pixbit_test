package com.abhinand.pixbittest.login.domain.repository

import com.abhinand.pixbittest.core.network.NetworkResource
import com.abhinand.pixbittest.login.data.remote.dto.LoginRequest
import com.abhinand.pixbittest.login.domain.model.Login
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun login(loginRequest: LoginRequest): NetworkResource<Login>

    suspend fun saveToken(token: String)

    fun isLoggedIn(): Flow<Boolean>

}