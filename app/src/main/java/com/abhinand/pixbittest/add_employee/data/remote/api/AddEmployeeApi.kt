package com.abhinand.pixbittest.add_employee.data.remote.api

import com.abhinand.pixbittest.add_employee.data.remote.dto.AddEmployeeResponseDto
import com.abhinand.pixbittest.add_employee.data.remote.dto.DesignationDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap

interface AddEmployeeApi {

    @GET("designations")
    suspend fun getDesignations(): List<DesignationDto>

    @Multipart
    @POST("employees")
    suspend fun addEmployee(
        @PartMap parts: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part profile_pic: MultipartBody.Part,
        @Part resume: MultipartBody.Part
    ): AddEmployeeResponseDto

}