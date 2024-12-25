package ir.androidcoder.data.paging.watchList

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.androidcoder.data.local.MoviesDao
import ir.androidcoder.data.mapper.toDomain
import ir.androidcoder.domain.entities.WatchListDEntity

class WatchListPagingSource (private val dao: MoviesDao , private val query: String) : PagingSource<Int , WatchListDEntity>() {

    override fun getRefreshKey(state: PagingState<Int, WatchListDEntity>): Int? = state.anchorPosition?.let { anchor ->
        state.closestPageToPosition(anchor)?.prevKey?.plus(1) ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WatchListDEntity> = try {

        val currentPage = params.key ?: 1
        val response = dao.getWatchListByTitle(title = query)

        LoadResult.Page(
            data = response.map { it.toDomain() },
            prevKey = if (currentPage == 1) null else currentPage - 1,
            nextKey = if (response.isEmpty()) null else currentPage + 1
        )
    }catch (e : Exception){
        LoadResult.Error(e)
    }
}