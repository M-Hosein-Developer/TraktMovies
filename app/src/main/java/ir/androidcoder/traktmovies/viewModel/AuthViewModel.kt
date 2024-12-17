package ir.androidcoder.traktmovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.androidcoder.domain.usecase.AuthUsecase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val usecase: AuthUsecase) : ViewModel() {

    fun getAccessToken(code: String, clientId: String, clientSecret: String){
        viewModelScope.launch {
            usecase.getAccessToken(code , clientId , clientSecret)
        }
    }

}