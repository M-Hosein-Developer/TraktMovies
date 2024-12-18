package ir.androidcoder.domain.repository

interface AuthRepository {

    suspend fun getAccessToken(code: String, clientId: String, clientSecret: String)


    suspend fun logout(clientId: String , clientSecret: String)

}