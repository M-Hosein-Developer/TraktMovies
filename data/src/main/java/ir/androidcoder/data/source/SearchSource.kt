package ir.androidcoder.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import ir.androidcoder.data.paging.remotePagingSource.SearchPagingSource
import ir.androidcoder.data.remote.TMDBApiService
import ir.androidcoder.domain.entities.SearchEntity
import javax.inject.Inject

class SearchSource @Inject constructor(private val api: TMDBApiService) {

    fun searchMovies(query: String) : Pager<Int , SearchEntity.Result> = Pager(
        config = PagingConfig(pageSize = 20 , enablePlaceholders = false),
        pagingSourceFactory = { SearchPagingSource(api , query) }
    )

}