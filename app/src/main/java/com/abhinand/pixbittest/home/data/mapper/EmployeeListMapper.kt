package com.abhinand.pixbittest.home.data.mapper

import com.abhinand.pixbittest.home.data.remote.dto.EmployeeListDto
import com.abhinand.pixbittest.home.domain.model.EmployeeList

fun EmployeeListDto.toDomain() = EmployeeList(
    employees = data.map { it.toDomain() },
    meta = meta
)
