package com.abhinand.pixbittest.core.network

import com.abhinand.pixbittest.core.data.DataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val dataStore: DataStore) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val invocation = chain.request().tag(Invocation::class.java)
        val noAuth = invocation?.method()?.isAnnotationPresent(NoAuth::class.java) == true
        if (noAuth) {
            return chain.proceed(chain.request())
        }

        val token = runBlocking {
            dataStore.getToken().first()
        }
        val request = chain.request().newBuilder()
        if (token != null) {
            request.addHeader("Authorization", "Bearer $token")
        }
        return chain.proceed(request.build())
    }
}