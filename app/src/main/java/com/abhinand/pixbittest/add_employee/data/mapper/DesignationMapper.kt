package com.abhinand.pixbittest.add_employee.data.mapper

import com.abhinand.pixbittest.add_employee.data.remote.dto.DesignationDto
import com.abhinand.pixbittest.add_employee.domain.model.Designation

fun DesignationDto.toDesignation(): Designation {
    return Designation(
        id = id,
        name = name
    )
}