package com.abhinand.pixbittest.home.data.remote.api

import com.abhinand.pixbittest.home.data.remote.dto.EmployeeDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {

    @GET("employees")
    suspend fun getEmployees(@Query("page") page: Int): List<EmployeeDto>

}