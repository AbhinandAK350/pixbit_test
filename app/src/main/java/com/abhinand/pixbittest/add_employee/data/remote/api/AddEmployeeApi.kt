package com.abhinand.pixbittest.add_employee.data.remote.api

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
        @Part("first_name") firstName: RequestBody,
        @Part("last_name") lastName: RequestBody,
        @Part("date_of_birth") dateOfBirth: RequestBody,
        @Part("designation") designation: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("mobile_number") mobileNumber: RequestBody,
        @Part("email") email: RequestBody,
        @Part("address") address: RequestBody,

        @Part profilePic: MultipartBody.Part,
        @Part resume: MultipartBody.Part,

        @Part("contract_period") contractPeriod: RequestBody,
        @Part("total_salary") totalSalary: RequestBody,

        @PartMap monthlyPayments: Map<String, @JvmSuppressWildcards RequestBody>
    )

}