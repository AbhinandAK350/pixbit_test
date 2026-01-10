package com.abhinand.pixbittest.add_employee.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddEmployeeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(AddEmployeeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.onEach {
            _uiState.update { state ->
                state.copy(
                    isNextButtonEnabled = state.firstName.isNotEmpty() &&
                            state.lastName.isNotEmpty() &&
                            state.dob.isNotEmpty() &&
                            state.gender.isNotEmpty() &&
                            state.designation.isNotEmpty()
                )
            }
        }.launchIn(viewModelScope)
    }

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
        _uiState.update { it.copy(gender = gender, isGenderDropdownOpen = false) }
    }

    fun onDesignationChange(designation: String) {
        _uiState.update { it.copy(designation = designation, isDesignationDropdownOpen = false) }
    }

    fun onShowDatePickerChange(show: Boolean) {
        _uiState.update { it.copy(showDatePicker = show) }
    }

    fun onGenderDropdownOpenChange(isOpen: Boolean) {
        _uiState.update { it.copy(isGenderDropdownOpen = isOpen) }
    }

    fun onDesignationDropdownOpenChange(isOpen: Boolean) {
        _uiState.update { it.copy(isDesignationDropdownOpen = isOpen) }
    }
}