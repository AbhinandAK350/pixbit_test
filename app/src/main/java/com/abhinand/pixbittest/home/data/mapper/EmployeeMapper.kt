package com.abhinand.pixbittest.home.data.mapper

import com.abhinand.pixbittest.home.data.remote.dto.EmployeeDto
import com.abhinand.pixbittest.home.domain.model.Employee

fun EmployeeDto.toDomain(): Employee {
    return Employee(
        name = name
    )
}