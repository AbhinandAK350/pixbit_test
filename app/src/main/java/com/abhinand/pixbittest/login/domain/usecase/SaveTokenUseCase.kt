package com.abhinand.pixbittest.login.domain.usecase

import com.abhinand.pixbittest.login.domain.repository.LoginRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend operator fun invoke(token: String) {
        loginRepository.saveToken(token)
    }

}