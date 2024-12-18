package ir.androidcoder.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun getAccessToken(code: String, clientId: String, clientSecret: String) : Flow<Boolean>


    suspend fun logout(clientId: String , clientSecret: String)

}