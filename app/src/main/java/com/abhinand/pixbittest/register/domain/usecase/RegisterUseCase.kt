package com.abhinand.pixbittest.register.domain.usecase

import com.abhinand.pixbittest.core.network.NetworkResource
import com.abhinand.pixbittest.register.data.remote.dto.RegisterRequest
import com.abhinand.pixbittest.register.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository: RegisterRepository) {
    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): NetworkResource<Unit> = repository.register(
        registerRequest = RegisterRequest(
            name = name,
            email = email,
            password = password,
            confirm_password = confirmPassword
        )
    )
}