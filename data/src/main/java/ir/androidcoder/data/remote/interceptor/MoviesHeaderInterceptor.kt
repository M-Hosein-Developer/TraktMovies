package ir.androidcoder.data.remote.interceptor

import ir.androidcoder.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class MoviesHeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val requestWithHeaders = originalRequest.newBuilder()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", BuildConfig.AUTHORIZATION_TMDB)
            .build()

        return chain.proceed(requestWithHeaders)
    }
}