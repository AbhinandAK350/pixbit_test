package com.abhinand.pixbittest.add_employee.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhinand.pixbittest.add_employee.domain.usecase.GetDesignationUseCase
import com.abhinand.pixbittest.core.network.NetworkResource
import com.abhinand.pixbittest.register.domain.valueobject.Email
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddEmployeeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getDesignationUseCase: GetDesignationUseCase
) : ViewModel() {

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
                            state.designation.isNotEmpty(),
                    isContactNextButtonEnabled = state.address.isNotEmpty() &&
                            state.email.isNotEmpty() &&
                            state.isEmailValid &&
                            state.mobileNumber.isNotEmpty() &&
                            state.mobileNumber.length == 10
                )
            }
        }.launchIn(viewModelScope)

        viewModelScope.launch {
            when (val result = getDesignationUseCase()) {
                is NetworkResource.Success -> {
                    _uiState.update {
                        it.copy(designationOptions = result.data?.map { desig -> desig.name }
                            ?: emptyList())
                    }
                }

                is NetworkResource.Error -> Log.e(
                    "AddEmployeeViewModel",
                    "getDesignations: ${result.message}"
                )
            }
        }
    }

    fun onCurrentStepChange(step: AddEmployeeStep) {
        _uiState.update { it.copy(currentStep = step) }
    }

    fun onProfileImageChange(uri: Uri?) {
        _uiState.update { it.copy(profileImage = uri) }
    }

    @SuppressLint("Range")
    fun onResumeFileChange(uri: Uri?) {
        uri ?: return
        var fileName = ""
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
            }
        }

        _uiState.update { it.copy(resumeFile = uri, resumeFileName = fileName) }
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

    fun onShowDatePickerChange(datePickerTarget: DatePickerTarget) {
        _uiState.update { it.copy(showDatePicker = true, datePickerTarget = datePickerTarget) }
    }

    fun closeDatePicker() {
        _uiState.update {
            it.copy(
                showDatePicker = false,
                datePickerTarget = null
            )
        }
    }

    fun onGenderDropdownOpenChange(isOpen: Boolean) {
        _uiState.update { it.copy(isGenderDropdownOpen = isOpen) }
    }

    fun onDesignationDropdownOpenChange(isOpen: Boolean) {
        _uiState.update { it.copy(isDesignationDropdownOpen = isOpen) }
    }

    fun onMobileNumberChange(mobileNumber: String) {
        _uiState.update { it.copy(mobileNumber = mobileNumber) }
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
    }

    fun onAddressChange(address: String) {
        _uiState.update { it.copy(address = address) }
    }

    fun onAmountChange(amount: String) {
        _uiState.update { it.copy(amount = amount) }
    }

    fun onRemarksChange(remarks: String) {
        _uiState.update { it.copy(remarks = remarks) }
    }

    fun onPaymentDateChange(date: String) {
        _uiState.update { it.copy(paymentDate = date) }
    }

    fun onSaveClick() {

    }
}
