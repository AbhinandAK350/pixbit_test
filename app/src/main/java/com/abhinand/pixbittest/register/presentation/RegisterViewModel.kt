package com.abhinand.pixbittest.register.presentation

import androidx.lifecycle.ViewModel
import com.abhinand.pixbittest.register.domain.valueobject.Email
import com.abhinand.pixbittest.register.domain.valueobject.Name
import com.abhinand.pixbittest.register.domain.valueobject.Password
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    fun onNameChange(name: String) {
        val isNameValid = Name.create(name).isSuccess
        _uiState.update {
            it.copy(
                name = name,
                isNameValid = isNameValid,
                nameTouched = true
            )
        }
        updateRegisterButtonState()
    }

    fun onEmailChange(email: String) {
        val isEmailValid = Email.create(email).isSuccess
        _uiState.update {
            it.copy(
                email = email,
                isEmailValid = isEmailValid,
                emailTouched = true
            )
        }
        updateRegisterButtonState()
    }

    fun onPasswordChange(password: String) {
        val isPasswordValid = Password.create(password).isSuccess
        _uiState.update {
            it.copy(
                password = password,
                isPasswordValid = isPasswordValid,
                isConfirmPasswordValid = it.confirmPassword.isNotEmpty() && password == it.confirmPassword,
                passwordTouched = true
            )
        }
        updateRegisterButtonState()
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        val isConfirmPasswordValid = confirmPassword == _uiState.value.password
        _uiState.update {
            it.copy(
                confirmPassword = confirmPassword,
                isConfirmPasswordValid = isConfirmPasswordValid,
                confirmPasswordTouched = true
            )
        }
        updateRegisterButtonState()
    }

    fun onPasswordVisibilityChange() {
        _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun onConfirmPasswordVisibilityChange() {
        _uiState.update { it.copy(isConfirmPasswordVisible = !it.isConfirmPasswordVisible) }
    }

    fun onNameFocusLost() {
        _uiState.update { it.copy(nameTouched = true) }
    }

    fun onEmailFocusLost() {
        _uiState.update { it.copy(emailTouched = true) }
    }

    fun onPasswordFocusLost() {
        _uiState.update { it.copy(passwordTouched = true) }
    }

    fun onConfirmPasswordFocusLost() {
        _uiState.update { it.copy(confirmPasswordTouched = true) }
    }

    fun onRegisterButtonClick() {
        // TODO: Implement registration logic
    }

    private fun updateRegisterButtonState() {
        _uiState.update {
            val allFieldsValid = it.isNameValid &&
                    it.isEmailValid &&
                    it.isPasswordValid &&
                    it.isConfirmPasswordValid &&
                    it.name.isNotEmpty() &&
                    it.email.isNotEmpty() &&
                    it.password.isNotEmpty() &&
                    it.confirmPassword.isNotEmpty()

            it.copy(isRegisterButtonEnabled = allFieldsValid)
        }
    }
}
