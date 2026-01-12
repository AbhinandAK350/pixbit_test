package com.abhinand.pixbittest.login.data.mapper

import com.abhinand.pixbittest.login.data.remote.dto.LoginResponseDto
import com.abhinand.pixbittest.login.domain.model.Login

fun LoginResponseDto.toDomain(): Login {
    return Login(
        token = access_token,
        error = error
    )
}