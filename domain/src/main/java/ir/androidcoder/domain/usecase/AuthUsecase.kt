package ir.androidcoder.domain.usecase

import ir.androidcoder.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthUsecase(private val repository: AuthRepository) {

    fun authorization() = repository.authorization()

    suspend fun getAccessToken(code: String) : Flow<Boolean> = repository.getAccessToken(code)

    suspend fun logout(){
        repository.logout()
    }

}