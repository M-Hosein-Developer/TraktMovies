package ir.androidcoder.traktmovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.androidcoder.domain.usecase.SearchUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val usecase: SearchUsecase) : ViewModel() {

    var _searchQuery = MutableStateFlow("")
    val searchQuery : StateFlow<String> get() = _searchQuery

    fun searchMovies(query: String) = usecase.searchMovies(query).flow.cachedIn(viewModelScope)


}