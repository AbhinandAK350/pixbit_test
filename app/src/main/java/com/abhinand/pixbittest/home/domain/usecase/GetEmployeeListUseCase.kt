package com.abhinand.pixbittest.home.domain.usecase

import com.abhinand.pixbittest.home.domain.repository.HomeRepository
import javax.inject.Inject

class GetEmployeeListUseCase @Inject constructor(private val repository: HomeRepository) {
    suspend operator fun invoke(page: Int) = repository.getEmployeeList(page)
}