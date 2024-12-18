package ir.androidcoder.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.androidcoder.data.source.AuthSource
import ir.androidcoder.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val source : AuthSource , @ApplicationContext private val context: Context) : AuthRepository {

    override suspend fun getAccessToken(code: String, clientId: String, clientSecret: String){
        source.getAccessToken(context , code , clientId , clientSecret)
    }



    override suspend fun logout(clientId: String , clientSecret: String) {
        source.logout(context , clientId , clientSecret)
    }
}