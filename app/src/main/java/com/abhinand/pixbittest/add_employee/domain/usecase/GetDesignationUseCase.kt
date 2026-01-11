package com.abhinand.pixbittest.add_employee.domain.usecase

import com.abhinand.pixbittest.add_employee.domain.repository.AddEmployeeRepository
import javax.inject.Inject

class GetDesignationUseCase @Inject constructor(private val repository: AddEmployeeRepository) {

    suspend operator fun invoke() = repository.getDesignations()

}