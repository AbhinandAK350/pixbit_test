package com.abhinand.pixbittest.core.network

import android.util.Log
import com.abhinand.pixbittest.core.data.DataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataStore: DataStore
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val invocation = chain.request().tag(Invocation::class.java)
        val noAuth = invocation?.method()
            ?.isAnnotationPresent(NoAuth::class.java) == true

        val builder = chain.request().newBuilder()
            .addHeader("Accept", "application/json")

        if (!noAuth) {
            val token = runBlocking {
                dataStore.getToken().first()
            }

            Log.d("Token", token.toString())

            require(!token.isNullOrBlank()) {
                "Authorization token missing"
            }

            builder.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(builder.build())
    }
}
