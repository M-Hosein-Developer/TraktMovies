package ir.androidcoder.traktmovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.androidcoder.domain.usecase.MoviesUsecase
import ir.androidcoder.traktmovies.BuildConfig
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val usecase: MoviesUsecase) : ViewModel() {


    val mowPlaying = usecase.allNowPlaying(BuildConfig.AUTHORIZATION_TMDB).flow.cachedIn(viewModelScope)

    val popular = usecase.allPopular(BuildConfig.AUTHORIZATION_TMDB).flow.cachedIn(viewModelScope)

    val topRate = usecase.allTopRate(BuildConfig.AUTHORIZATION_TMDB).flow.cachedIn(viewModelScope)

}