package com.abhinand.pixbittest.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhinand.pixbittest.core.navigation.Action
import com.abhinand.pixbittest.core.navigation.Screen
import com.abhinand.pixbittest.core.network.NetworkResource
import com.abhinand.pixbittest.login.domain.usecase.LoginUseCase
import com.abhinand.pixbittest.login.domain.usecase.SaveTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
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

    fun onLoginClick(onNavigate: (Action) -> Unit) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val result =
                loginUseCase(email = _uiState.value.email, password = _uiState.value.password)

            when (result) {
                is NetworkResource.Success -> {
                    result.data?.token?.let {
                        saveTokenUseCase(it)
                        onNavigate(Action.Push(Screen.Home, clearStack = true))
                    }
                }

                is NetworkResource.Error -> {
                    saveTokenUseCase("joyrijyo")
                    _uiState.value = _uiState.value.copy(errorMessage = result.message)
                }
            }

            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }

    fun dismissErrorDialog() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

}