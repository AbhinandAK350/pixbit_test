package com.abhinand.pixbittest.add_employee.presentation

import android.net.Uri
import com.abhinand.pixbittest.core.utils.Util

data class AddEmployeeUiState(
    val currentStep: AddEmployeeStep = AddEmployeeStep.BASIC_DETAILS,
    val profileImage: Uri? = null,
    val firstName: String = "",
    val lastName: String = "",
    val dob: String = Util.getTodayDate(),
    val gender: String = "",
    val isGenderDropdownOpen: Boolean = false,
    val genderOptions: List<String> = listOf("Male", "Female", "Other"),
    val designation: String = "",
    val isDesignationDropdownOpen: Boolean = false,
    val designationOptions: List<String> = listOf("Designer", "Developer", "QA"),
    val showDatePicker: Boolean = false,
    val resumeFile: Uri? = null,
    val isNextButtonEnabled: Boolean = false
)