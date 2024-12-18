package ir.androidcoder.data.source

import android.content.Context
import android.util.Log
import ir.androidcoder.data.remote.TraktApiService
import ir.androidcoder.data.util.TokenManager
import kotlinx.coroutines.delay
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

}