package com.abhinand.pixbittest.core.di

import com.abhinand.pixbittest.core.data.DataStore
import com.abhinand.pixbittest.core.network.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InterceptorModule {

    @Provides
    @Singleton
    fun provideAuthInterceptor(dataStore: DataStore) = AuthInterceptor(dataStore)

}