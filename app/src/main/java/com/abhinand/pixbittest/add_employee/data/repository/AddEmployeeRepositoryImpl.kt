package com.abhinand.pixbittest.add_employee.data.repository

import android.util.Log
import com.abhinand.pixbittest.add_employee.data.mapper.toDesignation
import com.abhinand.pixbittest.add_employee.data.remote.api.AddEmployeeApi
import com.abhinand.pixbittest.add_employee.domain.model.Designation
import com.abhinand.pixbittest.add_employee.domain.repository.AddEmployeeRepository
import com.abhinand.pixbittest.core.network.NetworkResource
import com.abhinand.pixbittest.core.network.NetworkUtils
import com.abhinand.pixbittest.core.network.toNetworkError
import com.abhinand.pixbittest.core.network.toUserMessage
import javax.inject.Inject

class AddEmployeeRepositoryImpl @Inject constructor(
    private val api: AddEmployeeApi,
    private val networkUtils: NetworkUtils
) : AddEmployeeRepository {
    override suspend fun getDesignations(): NetworkResource<List<Designation>> {
        if (!networkUtils.isNetworkAvailable()) {
            return NetworkResource.Error("No internet connection")
        }
        return try {
            val response = api.getDesignations()
            if (response.success) {
                NetworkResource.Success(response.data.map { it.toDesignation() })
            } else {
                NetworkResource.Error("Registration failed")
            }
        } catch (e: Exception) {
            Log.e("RegisterRepositoryImpl", "register: ", e)
            val error = e.toNetworkError()
            NetworkResource.Error(error.toUserMessage())
        }
    }
}