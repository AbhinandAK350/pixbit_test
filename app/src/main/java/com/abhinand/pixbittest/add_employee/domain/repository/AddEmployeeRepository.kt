package com.abhinand.pixbittest.add_employee.domain.repository

import com.abhinand.pixbittest.add_employee.domain.model.Designation
import com.abhinand.pixbittest.core.network.NetworkResource

interface AddEmployeeRepository {

    suspend fun getDesignations(): NetworkResource<List<Designation>>

}