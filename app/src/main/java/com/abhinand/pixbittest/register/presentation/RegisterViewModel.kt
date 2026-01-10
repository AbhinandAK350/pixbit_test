package com.abhinand.pixbittest.register.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.abhinand.pixbittest.register.domain.valueobject.Email
import com.abhinand.pixbittest.register.domain.valueobject.Name
import com.abhinand.pixbittest.register.domain.valueobject.Password
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    fun onNameChange(name: String) {
        val valid = Name.create(name).isSuccess
        updateState(name = name, isNameValid = valid)
    }

    fun onEmailChange(email: String) {
        val valid = Email.create(email).isSuccess
        updateState(email = email, isEmailValid = valid)
    }

    fun onPasswordChange(password: String) {
        val isPasswordValid = Password.create(password).isSuccess
        updateState(
            password = password,
            isPasswordValid = isPasswordValid,
        )
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        val valid = confirmPassword == _uiState.value.password
        updateState(
            confirmPassword = confirmPassword,
            isConfirmPasswordValid = valid
        )
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

    private fun updateState(
        name: String = _uiState.value.name,
        email: String = _uiState.value.email,
        password: String = _uiState.value.password,
        confirmPassword: String = _uiState.value.confirmPassword,
        isNameValid: Boolean = _uiState.value.isNameValid,
        isEmailValid: Boolean = _uiState.value.isEmailValid,
        isPasswordValid: Boolean = _uiState.value.isPasswordValid,
        isConfirmPasswordValid: Boolean = _uiState.value.isConfirmPasswordValid
    ) {
        val enabled = isNameValid && isEmailValid && isPasswordValid && isConfirmPasswordValid

        Log.d(
            "RegisterViewModel",
            "Validation state: isNameValid=$isNameValid, isEmailValid=$isEmailValid, isPasswordValid=$isPasswordValid, isConfirmPasswordValid=$isConfirmPasswordValid"
        )
        Log.d("RegisterViewModel", "enabled: $enabled")

        _uiState.value = _uiState.value.copy(
            name = name,
            email = email,
            password = password,
            confirmPassword = confirmPassword,
            isNameValid = isNameValid,
            isEmailValid = isEmailValid,
            isPasswordValid = isPasswordValid,
            isConfirmPasswordValid = isConfirmPasswordValid,
            isRegisterButtonEnabled = enabled
        )
    }
}