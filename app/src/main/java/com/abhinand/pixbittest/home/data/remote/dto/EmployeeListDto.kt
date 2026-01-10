package com.abhinand.pixbittest.home.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class EmployeeListDto(val success: Boolean, val data: List<EmployeeDto>)

@Serializable
data class EmployeeDto(val name: String)