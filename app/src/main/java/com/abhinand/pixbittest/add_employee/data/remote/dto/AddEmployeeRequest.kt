package com.abhinand.pixbittest.add_employee.data.remote.dto

import com.abhinand.pixbittest.add_employee.presentation.components.steps.PaymentDetail
import java.io.File

data class AddEmployeeRequest(
    val first_name: String,
    val last_name: String,
    val date_of_birth: String,
    val designationId: Int,
    val gender: String,
    val mobile_number: String,
    val email: String,
    val address: String,
    val profile_pic: File?,
    val resume: File?,
    val contract_period: Int,
    val total_salary: Int,
    val monthly_payments: List<PaymentDetail>
)