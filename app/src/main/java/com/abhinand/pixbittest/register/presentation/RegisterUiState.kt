package com.abhinand.pixbittest.register.presentation

data class RegisterUiState(
    val isLoading: Boolean = false,
    val isRegisterButtonEnabled: Boolean = false,

    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",

    val isPasswordVisible: Boolean = false,
    val isConfirmPasswordVisible: Boolean = false,

    val isNameValid: Boolean = false,
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isConfirmPasswordValid: Boolean = false
)