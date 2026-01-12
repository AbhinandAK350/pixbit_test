package com.abhinand.pixbittest.login.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val access_token: String? = null,
    val error: String? = null
)