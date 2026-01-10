package com.abhinand.pixbittest.login.domain.usecase

import com.abhinand.pixbittest.login.domain.repository.LoginRepository
import javax.inject.Inject

class IsLoggedInUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    operator fun invoke() = loginRepository.isLoggedIn()

}