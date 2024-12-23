package ir.androidcoder.data.paging.remotePagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.androidcoder.data.BuildConfig
import ir.androidcoder.data.mapper.toDomain
import ir.androidcoder.data.remote.TMDBApiService
import ir.androidcoder.domain.entities.SearchEntity
import kotlinx.coroutines.delay

class SearchPagingSource(private val api : TMDBApiService, private val query: String) : PagingSource<Int , SearchEntity.Result>() {

    override fun getRefreshKey(state: PagingState<Int, SearchEntity.Result>): Int? = state.anchorPosition?.let { anchor ->
        state.closestPageToPosition(anchor)?.prevKey?.plus(1) ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchEntity.Result> = try {
        val currentPage = params.key ?: 1
        delay(1000)
        val response = api.searchCollection(authorization = BuildConfig.AUTHORIZATION_TMDB , query = query , page = currentPage).body()?.toDomain()
        LoadResult.Page(
            data = response!!.results,
            prevKey = if (currentPage == 1) null else currentPage - 1,
            nextKey = if (response.results.isEmpty()) null else currentPage + 1
        )
    }catch (e : Exception){
        LoadResult.Error(e)
    }
}