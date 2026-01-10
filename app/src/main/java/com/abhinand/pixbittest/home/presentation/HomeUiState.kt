package com.abhinand.pixbittest.home.presentation

import com.abhinand.pixbittest.home.domain.model.Employee

data class HomeUiState(
    val isLoading: Boolean = false,
    val employees: List<Employee> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1
)