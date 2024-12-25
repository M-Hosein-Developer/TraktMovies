package ir.androidcoder.traktmovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.androidcoder.domain.entities.WatchListDEntity
import ir.androidcoder.domain.usecase.WatchListUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(private val usecase: WatchListUsecase) : ViewModel() {

    var _searchQuery = MutableStateFlow("")
    val searchQuery : StateFlow<String> get() = _searchQuery

    fun insertMovieToDb(movie : WatchListDEntity) = viewModelScope.launch{
        usecase.insertWatchList(movie)
    }

    val getAllWatchList = usecase.getAllWatchList().flow.cachedIn(viewModelScope)

    fun getMoviesBySearch(query : String) = usecase.getWatchlistBySearch(query).flow.cachedIn(viewModelScope)

}