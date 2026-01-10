package com.abhinand.pixbittest.login.di

import com.abhinand.pixbittest.core.network.NetworkUtils
import com.abhinand.pixbittest.login.data.remote.LoginApi
import com.abhinand.pixbittest.login.data.repository.LoginRepositoryImpl
import com.abhinand.pixbittest.login.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    @Singleton
    fun provideLoginApi(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(api: LoginApi, networkUtils: NetworkUtils): LoginRepository {
        return LoginRepositoryImpl(api, networkUtils)
    }
}
