package com.abhinand.pixbittest.home.di

import com.abhinand.pixbittest.core.network.NetworkUtils
import com.abhinand.pixbittest.home.data.remote.api.HomeApi
import com.abhinand.pixbittest.home.data.repository.HomeRepositoryImpl
import com.abhinand.pixbittest.home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeRepository(
        api: HomeApi,
        networkUtils: NetworkUtils
    ): HomeRepository {
        return HomeRepositoryImpl(api, networkUtils)
    }

    @Provides
    @Singleton
    fun provideHomeApi(retrofit: Retrofit): HomeApi =
        retrofit.create(HomeApi::class.java)
}
