package ir.androidcoder.traktmovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.androidcoder.domain.usecase.SearchUsecase
import ir.androidcoder.traktmovies.BuildConfig
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val usecase: SearchUsecase) : ViewModel() {


    fun searchMovies(query: String) = usecase.searchMovies(BuildConfig.AUTHORIZATION_TMDB , query).flow.cachedIn(viewModelScope)


}