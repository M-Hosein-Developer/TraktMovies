package ir.androidcoder.data.remote.interceptor

import android.content.Context
import ir.androidcoder.data.remote.TraktApiService
import ir.androidcoder.data.util.TokenManager

class RefreshToken (private val context: Context , private val api : TraktApiService , private val tokenManager: TokenManager) {

    suspend fun refreshToken(clientId : String , clientSecret : String) {
        val accessToken = TokenManager(context).getAccessToken()

        if (accessToken.isNullOrEmpty()){
            val refreshToken = tokenManager.getRefreshToken()
            if (!refreshToken.isNullOrEmpty()){
                val newTokenResponse = api.refreshAccessToken(
                    clientId,
                    clientSecret,
                    refreshToken
                )

                tokenManager.saveAccessToken(newTokenResponse.access_token)
                tokenManager.saveRefreshToken(newTokenResponse.refresh_token)
            }
        }
    }

}