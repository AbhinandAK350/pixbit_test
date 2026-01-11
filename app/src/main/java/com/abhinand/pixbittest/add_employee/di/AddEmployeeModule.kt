package com.abhinand.pixbittest.add_employee.di

import com.abhinand.pixbittest.add_employee.data.remote.api.AddEmployeeApi
import com.abhinand.pixbittest.add_employee.data.repository.AddEmployeeRepositoryImpl
import com.abhinand.pixbittest.add_employee.domain.repository.AddEmployeeRepository
import com.abhinand.pixbittest.core.network.NetworkUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AddEmployeeModule {

    @Provides
    @Singleton
    fun provideAddEmployeeRepository(
        api: AddEmployeeApi,
        networkUtils: NetworkUtils
    ): AddEmployeeRepository {
        return AddEmployeeRepositoryImpl(api, networkUtils)
    }

    @Provides
    @Singleton
    fun provideAddEmployeeApi(retrofit: Retrofit): AddEmployeeApi {
        return retrofit.create(AddEmployeeApi::class.java)
    }
}