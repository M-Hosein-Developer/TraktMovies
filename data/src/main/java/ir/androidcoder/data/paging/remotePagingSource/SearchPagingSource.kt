package ir.androidcoder.data.paging.remotePagingSource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.androidcoder.data.mapper.toDomain
import ir.androidcoder.data.remote.TMDBApiService
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
        val response = api.searchCollection(page = currentPage, query = query).results
        Log.v("testData122", response.toString())
        Log.v("testData1222", currentPage.toString())

        LoadResult.Page(
            data = if (response.isNotEmpty()) response.map { it.toDomain() } else emptyList(),
            prevKey = if (currentPage == 1) null else currentPage - 1,
            nextKey = if (response.isEmpty()) null else currentPage + 1
        )

    }catch (e : Exception){
        LoadResult.Error(e)
    }

}