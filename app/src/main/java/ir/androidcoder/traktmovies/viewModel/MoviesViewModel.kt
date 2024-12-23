package ir.androidcoder.traktmovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.androidcoder.domain.entities.MovieDetailEntity
import ir.androidcoder.domain.entities.YoutubeEntity
import ir.androidcoder.domain.usecase.MoviesUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val usecase: MoviesUsecase) : ViewModel() {


    private val _movieDetail = MutableStateFlow<MovieDetailEntity?>(null)
    val movieDetail : StateFlow<MovieDetailEntity?> get() = _movieDetail

    private val _youtubeUrl = MutableStateFlow<List<YoutubeEntity>?>(null)
    val youtubeUrl : StateFlow<List<YoutubeEntity>?> get() = _youtubeUrl


    val mowPlaying = usecase.allNowPlaying().flow.cachedIn(viewModelScope)

    val popular = usecase.allPopular().flow.cachedIn(viewModelScope)

    val topRate = usecase.allTopRate().flow.cachedIn(viewModelScope)

    val upcoming = usecase.allUpcoming().flow.cachedIn(viewModelScope)


    fun getMovieDetail(id : Int) = viewModelScope.launch {
        _movieDetail.value = usecase.getMovieDetail(id)
        _youtubeUrl.value = usecase.getYoutubeVideo(id)
    }

}