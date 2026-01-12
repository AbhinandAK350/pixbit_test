package com.abhinand.pixbittest.home.data.mapper

import com.abhinand.pixbittest.home.data.remote.dto.EmployeeDto
import com.abhinand.pixbittest.home.domain.model.Employee

fun EmployeeDto.toDomain(): Employee {
    return Employee(
        id = id,
        userId = user_id,
        firstName = first_name,
        lastName = last_name,
        profilePicUrl = profile_image_url,
        resume = resume,
        dob = date_of_birth,
        gender = gender,
        email = email,
        designation = designation,
        mobileNumber = mobile_number,
        address = address,
        contractPeriod = contract_period,
        totalSalary = total_salary,
        monthlyPayments = monthly_payments,
        createdAt = created_at
    )
}