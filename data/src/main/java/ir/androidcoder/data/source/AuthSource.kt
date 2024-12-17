package ir.androidcoder.data.source

import android.content.Context
import android.util.Log
import ir.androidcoder.data.remote.TraktApiService
import ir.androidcoder.data.util.TokenManager
import javax.inject.Inject

class AuthSource @Inject constructor(private val api: TraktApiService) {

    //get code and save token
    suspend fun getAccessToken(
        context: Context,
        code: String,
        clientId: String,
        clientSecret: String
    ) {
        if (api.getAccessToken(clientId, clientSecret, code).isSuccessful)
            TokenManager(context).saveAccessToken(
                api.getAccessToken(
                    clientId,
                    clientSecret,
                    code
                ).body()!!.access_token
            )
    }


}