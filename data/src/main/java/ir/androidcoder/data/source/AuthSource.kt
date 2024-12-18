package ir.androidcoder.data.source

import android.content.Context
import ir.androidcoder.data.remote.TraktApiService
import ir.androidcoder.data.util.TokenManager
import javax.inject.Inject

class AuthSource @Inject constructor(private val api: TraktApiService) {

    //get and save token
    suspend fun getAccessToken(
        context: Context, code: String, clientId: String, clientSecret: String
    ) {
        val response =  api.getAccessToken(clientId, clientSecret, code).body()

        if (response != null){
            TokenManager(context).saveAccessToken(response.access_token)
            TokenManager(context).saveRefreshToken(response.refresh_token)
        }

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