package com.abhinand.pixbittest.add_employee.domain.repository

import com.abhinand.pixbittest.add_employee.data.remote.dto.AddEmployeeRequest
import com.abhinand.pixbittest.add_employee.domain.model.Designation
import com.abhinand.pixbittest.core.network.NetworkResource

interface AddEmployeeRepository {

    suspend fun getDesignations(): NetworkResource<List<Designation>>

    suspend fun addEmployee(addEmployeeRequest: AddEmployeeRequest): NetworkResource<Unit>

}