package com.abhinand.pixbittest.add_employee.presentation

import android.net.Uri

data class AddEmployeeUiState(
    val currentStep: AddEmployeeStep = AddEmployeeStep.BASIC_DETAILS,
    val profileImage: Uri? = null,
    val firstName: String = "",
    val lastName: String = "",
    val dob: String = "",
    val gender: String = "",
    val maritalStatus: String = ""
)