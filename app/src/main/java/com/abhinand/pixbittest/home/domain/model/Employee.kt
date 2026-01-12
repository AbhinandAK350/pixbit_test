package com.abhinand.pixbittest.home.domain.model

import com.abhinand.pixbittest.home.data.remote.dto.MonthlyPaymentDto

data class Employee(
    val id: Int,
    val userId: Int,
    val firstName: String,
    val lastName: String,
    val profilePicUrl: String?,
    val resume: String?,
    val dob: String,
    val gender: String,
    val email: String,
    val designation: String,
    val mobileNumber: String,
    val address: String,
    val contractPeriod: Int,
    val totalSalary: Int,
    val monthlyPayments: List<MonthlyPaymentDto>,
    val createdAt: String
)