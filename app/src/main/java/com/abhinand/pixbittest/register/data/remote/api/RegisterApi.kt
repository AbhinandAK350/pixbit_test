package com.abhinand.pixbittest.register.data.remote.api

import com.abhinand.pixbittest.register.data.remote.dto.RegisterRequest
import com.abhinand.pixbittest.register.data.remote.dto.RegisterResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApi {

    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest): RegisterResponseDto

}