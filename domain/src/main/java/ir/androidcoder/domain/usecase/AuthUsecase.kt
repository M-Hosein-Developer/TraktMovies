package ir.androidcoder.domain.usecase

import ir.androidcoder.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthUsecase(private val repository: AuthRepository) {

    suspend fun getAccessToken(code: String, clientId: String, clientSecret: String) : Flow<Boolean> =
        repository.getAccessToken(code , clientId , clientSecret)


    suspend fun logout(clientId: String , clientSecret: String){
        repository.logout(clientId , clientSecret)
    }

}