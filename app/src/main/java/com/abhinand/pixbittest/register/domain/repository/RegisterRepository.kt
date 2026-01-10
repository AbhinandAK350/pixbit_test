package com.abhinand.pixbittest.register.domain.repository

import com.abhinand.pixbittest.core.network.NetworkResource
import com.abhinand.pixbittest.register.data.remote.dto.RegisterRequest

interface RegisterRepository {

    suspend fun register(registerRequest: RegisterRequest): NetworkResource<Unit>

}