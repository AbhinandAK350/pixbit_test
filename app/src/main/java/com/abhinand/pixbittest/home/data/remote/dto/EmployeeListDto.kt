package com.abhinand.pixbittest.home.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class EmployeeListDto(
    val data: List<EmployeeDto>,
    val meta: Meta
)

@Serializable
data class EmployeeDto(
    val id: Int,
    val user_id: Int,
    val first_name: String,
    val last_name: String,
    val profile_image_url: String?,
    val resume: String?,
    val date_of_birth: String,
    val gender: String,
    val email: String,
    val designation: String,
    val mobile_number: String,
    val address: String,
    val contract_period: Int,
    val total_salary: Int,
    val monthly_payments: List<MonthlyPaymentDto>,
    val created_at: String
)

@Serializable
data class MonthlyPaymentDto(
    val id: Int,
    val payment_date: String,
    val amount: Int,
    val amount_percentage: Int,
    val remarks: String?,
    val created_at: String
)

@Serializable
data class Meta(
    val total: Int
)
