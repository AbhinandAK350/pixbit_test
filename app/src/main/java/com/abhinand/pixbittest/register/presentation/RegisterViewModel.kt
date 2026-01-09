package com.abhinand.pixbittest.register.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    private fun checkRegisterButtonEnabled() {
        val currentState = _uiState.value
        val isEnabled = currentState.name.isNotEmpty() &&
                currentState.email.isNotEmpty() &&
                currentState.password.isNotEmpty() &&
                currentState.confirmPassword.isNotEmpty() &&
                currentState.password == currentState.confirmPassword
        _uiState.value = _uiState.value.copy(isRegisterButtonEnabled = isEnabled)
    }

    fun onNameChange(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
        checkRegisterButtonEnabled()
    }

    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
        checkRegisterButtonEnabled()
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
        checkRegisterButtonEnabled()
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _uiState.value = _uiState.value.copy(confirmPassword = confirmPassword)
        checkRegisterButtonEnabled()
    }

    fun onPasswordVisibilityChange() {
        _uiState.value = _uiState.value.copy(isPasswordVisible = !_uiState.value.isPasswordVisible)
    }

    fun onConfirmPasswordVisibilityChange() {
        _uiState.value =
            _uiState.value.copy(isConfirmPasswordVisible = !_uiState.value.isConfirmPasswordVisible)
    }

    fun onRegisterButtonClick() {

    }


}