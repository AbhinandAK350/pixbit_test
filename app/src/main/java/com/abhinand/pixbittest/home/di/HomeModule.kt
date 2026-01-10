package com.abhinand.pixbittest.home.di

import com.abhinand.pixbittest.home.data.remote.api.HomeApi
import com.abhinand.pixbittest.home.data.repository.HomeRepositoryImpl
import com.abhinand.pixbittest.home.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeModule {

    @Binds
    @Singleton
    abstract fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    companion object {

        @Provides
        @Singleton
        fun provideHomeApi(retrofit: Retrofit): HomeApi =
            retrofit.create(HomeApi::class.java)
    }
}
