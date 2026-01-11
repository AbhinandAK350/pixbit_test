package com.abhinand.pixbittest.add_employee.data.remote.api

import com.abhinand.pixbittest.add_employee.data.remote.dto.DesignationResponseDto
import retrofit2.http.GET

interface AddEmployeeApi {

    @GET("designations")
    suspend fun getDesignations(): DesignationResponseDto

}