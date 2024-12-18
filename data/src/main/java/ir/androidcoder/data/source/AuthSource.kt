package ir.androidcoder.data.source

import android.content.Context
import android.widget.Toast
import ir.androidcoder.data.remote.TraktApiService
import ir.androidcoder.data.remote.interceptor.RefreshToken
import ir.androidcoder.data.util.TokenManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthSource @Inject constructor(private val api: TraktApiService) {

    //get and save token
    suspend fun getAccessToken(context: Context, code: String, clientId: String, clientSecret: String) : Flow<Boolean>{

        return if (TokenManager(context).getAccessToken().isNullOrEmpty()) {
            val response = api.getAccessToken(clientId, clientSecret, code).body()
            if (response != null) {
                TokenManager(context).saveAccessToken(response.access_token)
                TokenManager(context).saveRefreshToken(response.refresh_token)
            }
            flow {
                emit(false)
            }
        }else {
            refreshToken(context, clientId, clientSecret)
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