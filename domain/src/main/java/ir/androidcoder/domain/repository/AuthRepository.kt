package ir.androidcoder.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun authorization() : String

    suspend fun getAccessToken(code: String) : Flow<Boolean>

    suspend fun logout(clientId: String , clientSecret: String)

}