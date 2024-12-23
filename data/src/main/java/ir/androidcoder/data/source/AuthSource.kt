package ir.androidcoder.data.source

import android.content.Context
import ir.androidcoder.data.BuildConfig
import ir.androidcoder.data.remote.TraktApiService
import ir.androidcoder.data.remote.interceptor.RefreshToken
import ir.androidcoder.data.util.TokenManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthSource @Inject constructor(private val api: TraktApiService) {

    //authorization

    fun authorization() : String = "https://api.trakt.tv/oauth/authorize?response_type=code&client_id=${BuildConfig.CLIENT_ID}&redirect_uri=${BuildConfig.REDIRECT_URL}"

    //get and save token
    suspend fun getAccessToken(context: Context, code: String) : Flow<Boolean>{

        return if (TokenManager(context).getAccessToken().isNullOrEmpty()) {
            val response = api.getAccessToken(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET, code).body()
            if (response != null && response.access_token != "" || response?.access_token != null) {
                response.access_token.let { TokenManager(context).saveAccessToken(it) }
                response.refresh_token.let { TokenManager(context).saveRefreshToken(it) }
            }
            flow {
                emit(false)
            }
        }else {
            refreshToken(context, BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET)
            flow {
                emit(true)
            }
        }
    }

    private suspend fun refreshToken(context: Context , clientId: String , clientSecret: String){
        RefreshToken(context , api , TokenManager(context)).refreshToken(clientId , clientSecret)
    }

    suspend fun logout(context: Context , clientId: String , clientSecret: String){
        if (TokenManager(context).getAccessToken() != null) {
            api.revokeToken(
                TokenManager(context).getAccessToken()!!,
                clientId,
                clientSecret
            )
            TokenManager(context).clearTokens()
        }
    }

}