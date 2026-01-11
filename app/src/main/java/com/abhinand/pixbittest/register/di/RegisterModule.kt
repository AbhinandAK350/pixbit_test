package com.abhinand.pixbittest.register.di

import com.abhinand.pixbittest.core.network.NetworkUtils
import com.abhinand.pixbittest.register.data.remote.api.RegisterApi
import com.abhinand.pixbittest.register.data.repository.RegisterRepositoryImpl
import com.abhinand.pixbittest.register.domain.repository.RegisterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegisterModule {

    @Provides
    @Singleton
    fun provideRegisterRepository(
        api: RegisterApi,
        networkUtils: NetworkUtils
    ): RegisterRepository {
        return RegisterRepositoryImpl(api, networkUtils)
    }

    @Provides
    @Singleton
    fun provideRegisterApi(retrofit: Retrofit): RegisterApi {
        return retrofit.create(RegisterApi::class.java)
    }
}