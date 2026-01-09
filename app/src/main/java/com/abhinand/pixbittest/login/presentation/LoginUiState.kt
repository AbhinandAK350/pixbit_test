package com.abhinand.pixbittest.login.presentation

data class LoginUiState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoginButtonEnabled: Boolean = false
)