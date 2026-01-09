package com.abhinand.pixbittest.login.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private fun checkLoginButtonEnabled() {
        val currentState = _uiState.value
        val isEnabled = currentState.email.isNotEmpty() && currentState.password.isNotEmpty()
        _uiState.value = _uiState.value.copy(isLoginButtonEnabled = isEnabled)
    }

    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
        checkLoginButtonEnabled()
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
        checkLoginButtonEnabled()
    }

    fun onPasswordVisibilityChange() {
        _uiState.value = _uiState.value.copy(isPasswordVisible = !_uiState.value.isPasswordVisible)
    }

    fun onLoginClick() {
    }

}