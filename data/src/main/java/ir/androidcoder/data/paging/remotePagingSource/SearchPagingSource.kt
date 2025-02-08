package ir.androidcoder.data.paging.remotePagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.androidcoder.data.mapper.toDomain
import ir.androidcoder.data.model.adapter.NetworkResponse
import ir.androidcoder.data.remote.TMDBApiService
import ir.androidcoder.domain.base.Error
import ir.androidcoder.domain.entities.SearchEntity
import kotlinx.coroutines.delay

class SearchPagingSource(private val api: TMDBApiService, private val query: String) :
    PagingSource<Int, SearchEntity>() {

    override fun getRefreshKey(state: PagingState<Int, SearchEntity>): Int? =
        state.anchorPosition?.let { anchor ->
        state.closestPageToPosition(anchor)?.prevKey?.plus(1) ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchEntity> = try {
        val currentPage = params.key ?: 1
        delay(1000)
        when(val response = api.searchCollection(page = currentPage, query = query)){
            is NetworkResponse.Success -> {
                val data = response.body.results
                LoadResult.Page(
                    data = if (data.isNotEmpty()) data.map { it.toDomain() } else emptyList(),
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (data.isEmpty()) null else currentPage + 1
                )
            }
            is NetworkResponse.NetworkError -> { LoadResult.Error(Error.Internet) }
            is NetworkResponse.ApiError -> {  LoadResult.Error(Error.ServerError("server")) }
            is NetworkResponse.UnknownError -> {  LoadResult.Error(Error.Unknown) }
        }


    }catch (e : Exception){
        LoadResult.Error(e)
    }

}