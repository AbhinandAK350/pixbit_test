package com.abhinand.pixbittest.home.domain.repository

import com.abhinand.pixbittest.core.network.NetworkResource
import com.abhinand.pixbittest.home.domain.model.EmployeeList

interface HomeRepository {

    suspend fun getEmployeeList(page: Int): NetworkResource<EmployeeList>

}