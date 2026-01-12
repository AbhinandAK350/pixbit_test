package com.abhinand.pixbittest.home.data.repository

import android.util.Log
import com.abhinand.pixbittest.core.network.NetworkResource
import com.abhinand.pixbittest.core.network.NetworkUtils
import com.abhinand.pixbittest.core.network.toNetworkError
import com.abhinand.pixbittest.core.network.toUserMessage
import com.abhinand.pixbittest.home.data.mapper.toDomain
import com.abhinand.pixbittest.home.data.remote.api.HomeApi
import com.abhinand.pixbittest.home.domain.model.Employee
import com.abhinand.pixbittest.home.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: HomeApi,
    private val networkUtils: NetworkUtils
) : HomeRepository {
    override suspend fun getEmployeeList(page: Int): NetworkResource<List<Employee>> {
        if (!networkUtils.isNetworkAvailable()) {
            return NetworkResource.Error("No internet connection")
        }
        return try {
            val response = api.getEmployees(page)
            if (response.data.isNotEmpty()) {
                NetworkResource.Success(response.data.map { it.toDomain() })
            } else {
                NetworkResource.Error("No employees found")
            }
        } catch (e: Exception) {
            Log.e("RegisterRepositoryImpl", "register: ", e)
            val error = e.toNetworkError()
            NetworkResource.Error(error.toUserMessage())
        }
    }
}