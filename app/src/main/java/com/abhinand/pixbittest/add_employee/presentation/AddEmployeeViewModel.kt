package com.abhinand.pixbittest.add_employee.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class AddEmployeeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(AddEmployeeUiState())
    val uiState = _uiState.asStateFlow()

    fun onCurrentStepChange(step: AddEmployeeStep) {
        _uiState.value = _uiState.value.copy(currentStep = step)
    }
}