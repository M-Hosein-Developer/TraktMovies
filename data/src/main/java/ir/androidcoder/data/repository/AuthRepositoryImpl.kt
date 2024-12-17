package ir.androidcoder.data.repository

import android.content.Context
import android.util.Log
import ir.androidcoder.data.source.AuthSource
import ir.androidcoder.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val source : AuthSource) : AuthRepository {

    @Inject lateinit var context : Context

    override suspend fun getAccessToken(code: String, clientId: String, clientSecret: String){
        source.getAccessToken(context , code , clientId , clientId)
        Log.v("testCode" , code)
    }

}