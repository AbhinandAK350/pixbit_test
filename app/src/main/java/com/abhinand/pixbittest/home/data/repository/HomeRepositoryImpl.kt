package com.abhinand.pixbittest.home.data.repository

import android.util.Log
import com.abhinand.pixbittest.core.network.NetworkResource
import com.abhinand.pixbittest.core.network.NetworkUtils
import com.abhinand.pixbittest.core.network.toNetworkError
import com.abhinand.pixbittest.core.network.toUserMessage
import com.abhinand.pixbittest.home.data.mapper.toDomain
import com.abhinand.pixbittest.home.data.remote.api.HomeApi
import com.abhinand.pixbittest.home.domain.model.EmployeeList
import com.abhinand.pixbittest.home.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: HomeApi,
    private val networkUtils: NetworkUtils
) : HomeRepository {
    override suspend fun getEmployeeList(page: Int): NetworkResource<EmployeeList> {
        if (!networkUtils.isNetworkAvailable()) {
            return NetworkResource.Error("No internet connection")
        }
        return try {
            val response = api.getEmployees(page)
            if (response.data.isNotEmpty()) {
                NetworkResource.Success(response.toDomain())
            } else {
                NetworkResource.Error("Add employees to view here")
            }
        } catch (e: Exception) {
            Log.e("RegisterRepositoryImpl", "register: ", e)
            val error = e.toNetworkError()
            NetworkResource.Error(error.toUserMessage())
        }
    }
}