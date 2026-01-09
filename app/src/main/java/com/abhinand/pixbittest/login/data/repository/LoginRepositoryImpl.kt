package com.abhinand.pixbittest.login.data.repository

import com.abhinand.pixbittest.core.network.NetworkResource
import com.abhinand.pixbittest.login.data.remote.dto.LoginRequest
import com.abhinand.pixbittest.login.domain.model.Login
import com.abhinand.pixbittest.login.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor() : LoginRepository {
    override suspend fun login(loginRequest: LoginRequest): NetworkResource<Login> {
        TODO()
    }
}