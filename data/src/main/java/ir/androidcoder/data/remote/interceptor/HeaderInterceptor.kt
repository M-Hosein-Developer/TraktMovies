package ir.androidcoder.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val requestWithHeaders = originalRequest.newBuilder()
            .addHeader("Content-Type", "application/json")
            .build()

        return chain.proceed(requestWithHeaders)
    }
}