package com.abhinand.pixbittest.register.data.mapper

import com.abhinand.pixbittest.register.data.remote.dto.RegisterResponseDto
import com.abhinand.pixbittest.register.domain.model.Register

fun RegisterResponseDto.toRegister(): Register {
    return Register(token = access_token)
}