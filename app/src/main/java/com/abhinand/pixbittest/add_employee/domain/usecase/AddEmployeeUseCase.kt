package com.abhinand.pixbittest.add_employee.domain.usecase

import com.abhinand.pixbittest.add_employee.data.remote.dto.AddEmployeeRequest
import com.abhinand.pixbittest.add_employee.domain.repository.AddEmployeeRepository
import javax.inject.Inject

class AddEmployeeUseCase @Inject constructor(private val repository: AddEmployeeRepository) {
    suspend operator fun invoke(addEmployeeRequest: AddEmployeeRequest) =
        repository.addEmployee(addEmployeeRequest)
}