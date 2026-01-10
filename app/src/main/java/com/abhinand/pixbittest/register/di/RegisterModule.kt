package com.abhinand.pixbittest.register.di

import com.abhinand.pixbittest.register.data.remote.api.RegisterApi
import com.abhinand.pixbittest.register.data.repository.RegisterRepositoryImpl
import com.abhinand.pixbittest.register.domain.repository.RegisterRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RegisterModule {

    @Binds
    @Singleton
    abstract fun bindRegisterRepository(
        registerRepositoryImpl: RegisterRepositoryImpl
    ): RegisterRepository

    companion object {
        @Provides
        @Singleton
        fun provideRegisterApi(retrofit: Retrofit): RegisterApi {
            return retrofit.create(RegisterApi::class.java)
        }
    }
}