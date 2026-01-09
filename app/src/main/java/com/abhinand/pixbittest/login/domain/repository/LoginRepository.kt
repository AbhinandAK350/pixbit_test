package com.abhinand.pixbittest.login.domain.repository

import com.abhinand.pixbittest.core.network.NetworkResource
import com.abhinand.pixbittest.login.data.remote.dto.LoginRequest
import com.abhinand.pixbittest.login.domain.model.Login

interface LoginRepository {

    suspend fun login(loginRequest: LoginRequest): NetworkResource<Login>

}