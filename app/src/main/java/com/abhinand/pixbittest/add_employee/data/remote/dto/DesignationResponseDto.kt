package com.abhinand.pixbittest.add_employee.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class DesignationResponseDto(val success: Boolean, val data: List<DesignationDto>)

@Serializable
data class DesignationDto(
    val id: Int,
    val name: String,
)