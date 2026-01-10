package com.abhinand.pixbittest.add_employee.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddEmployeeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(AddEmployeeUiState())
    val uiState = _uiState.asStateFlow()

    fun onCurrentStepChange(step: AddEmployeeStep) {
        _uiState.update { it.copy(currentStep = step) }
    }

    fun onProfileImageChange(uri: Uri?) {
        _uiState.update { it.copy(profileImage = uri) }
    }

    fun onFirstNameChange(name: String) {
        _uiState.update { it.copy(firstName = name) }
    }

    fun onLastNameChange(name: String) {
        _uiState.update { it.copy(lastName = name) }
    }

    fun onDobChange(dob: String) {
        _uiState.update { it.copy(dob = dob) }
    }

    fun onGenderChange(gender: String) {
        _uiState.update { it.copy(gender = gender) }
    }

    fun onMaritalStatusChange(status: String) {
        _uiState.update { it.copy(maritalStatus = status) }
    }
}