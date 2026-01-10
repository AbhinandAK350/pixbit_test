package com.abhinand.pixbittest.login.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val success: Boolean,
    val message: String? = null,
    val token: String? = null
)