package com.abhinand.pixbittest.register.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseDto(
    val access_token: String? = null,
    val error: String? = null,
    val email: String
)