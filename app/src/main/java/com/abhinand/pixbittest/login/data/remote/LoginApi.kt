package com.abhinand.pixbittest.login.data.remote

import com.abhinand.pixbittest.login.data.remote.dto.LoginRequest
import com.abhinand.pixbittest.login.data.remote.dto.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponseDto

}