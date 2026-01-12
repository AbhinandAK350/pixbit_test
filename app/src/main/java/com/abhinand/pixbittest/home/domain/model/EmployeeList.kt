package com.abhinand.pixbittest.home.domain.model

import com.abhinand.pixbittest.home.data.remote.dto.Meta

data class EmployeeList(
    val employees: List<Employee>,
    val meta: Meta
)
