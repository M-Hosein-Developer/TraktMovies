package ir.androidcoder.traktmovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.androidcoder.domain.usecase.AuthUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val usecase: AuthUsecase) : ViewModel() {

    private val _isAuth = MutableStateFlow<Boolean>(false)
    val isAuth : StateFlow<Boolean> get() = _isAuth

    fun getAccessToken(code: String, clientId: String, clientSecret: String){
        viewModelScope.launch {
            usecase.getAccessToken(code , clientId , clientSecret).collect { status ->
                _isAuth.value = status
            }
        }
    }

}