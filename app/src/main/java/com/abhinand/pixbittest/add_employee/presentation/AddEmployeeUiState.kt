package com.abhinand.pixbittest.add_employee.presentation

data class AddEmployeeUiState(
    val currentStep: AddEmployeeStep = AddEmployeeStep.BASIC_DETAILS
)