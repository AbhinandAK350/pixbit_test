package com.abhinand.pixbittest.add_employee.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhinand.pixbittest.add_employee.data.remote.dto.AddEmployeeRequest
import com.abhinand.pixbittest.add_employee.domain.model.Designation
import com.abhinand.pixbittest.add_employee.domain.usecase.AddEmployeeUseCase
import com.abhinand.pixbittest.add_employee.domain.usecase.GetDesignationUseCase
import com.abhinand.pixbittest.add_employee.presentation.components.steps.PaymentDetail
import com.abhinand.pixbittest.core.navigation.Action
import com.abhinand.pixbittest.core.navigation.Screen
import com.abhinand.pixbittest.core.network.NetworkResource
import com.abhinand.pixbittest.register.domain.valueobject.Email
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AddEmployeeViewModel @Inject constructor(
    private val getDesignationUseCase: GetDesignationUseCase,
    private val addEmployeeUseCase: AddEmployeeUseCase
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
                            state.designation.isNotEmpty() &&
                            state.resumeFile != null && state.profileImage != null,
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
                        it.copy(designationOptions = result.data ?: emptyList())
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

    fun onProfileImageChange(file: File?) {
        _uiState.update { it.copy(profileImage = file) }
    }

    fun onResumeFileChange(file: File?) {
        _uiState.update { it.copy(resumeFile = file, resumeFileName = file?.name) }
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

    fun onDesignationChange(designation: Designation) {
        _uiState.update {
            it.copy(
                designationId = designation.id,
                designation = designation.name,
                isDesignationDropdownOpen = false
            )
        }
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

    fun onPaymentDetailsChange(paymentDetail: PaymentDetail) {
        _uiState.update { state ->
            state.copy(
                paymentDetails = state.paymentDetails + paymentDetail
            )
        }
    }

    fun onDeletePaymentDetail(index: Int) {
        _uiState.update { state ->
            state.copy(
                paymentDetails = state.paymentDetails.filterIndexed { i, _ -> i != index }
            )
        }
    }

    fun onClearPaymentDetails() {
        _uiState.update { state ->
            state.copy(
                paymentDetails = emptyList()
            )
        }
    }

    fun dismissErrorDialog() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

    fun onSaveClick(salary: Float, period: Int, onNavigate: (Action) -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val addEmployeeRequest = AddEmployeeRequest(
                first_name = _uiState.value.firstName,
                last_name = _uiState.value.lastName,
                date_of_birth = _uiState.value.dob,
                designationId = _uiState.value.designationId,
                gender = _uiState.value.gender,
                mobile_number = _uiState.value.mobileNumber,
                email = _uiState.value.email,
                address = _uiState.value.address,
                profile_pic = _uiState.value.profileImage,
                resume = _uiState.value.resumeFile,
                contract_period = period,
                total_salary = salary.toDouble(),
                monthly_payments = _uiState.value.paymentDetails
            )

            Log.e("AddEmployeeViewModel", "onSaveClick: $addEmployeeRequest")

            val result = addEmployeeUseCase(addEmployeeRequest)

            _uiState.update { it.copy(isLoading = false) }

            when (result) {
                is NetworkResource.Success -> {
                    onNavigate(Action.Push(Screen.Home, clearStack = true))
                }

                is NetworkResource.Error -> {
                    _uiState.value = _uiState.value.copy(errorMessage = result.message)
                    Log.e("AddEmployeeViewModel", "onSaveClick: ${result.message}")
                }
            }
        }
    }

}
