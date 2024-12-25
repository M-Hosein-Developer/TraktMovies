package ir.androidcoder.data.paging.watchList

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.androidcoder.data.local.entity.WatchListEntity
import ir.androidcoder.data.mapper.toDomain
import ir.androidcoder.domain.entities.WatchListDEntity

class WatchListConverterSource(private val source: PagingSource<Int, WatchListEntity>
) : PagingSource<Int, WatchListDEntity>() {

    override fun getRefreshKey(state: PagingState<Int, WatchListDEntity>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val closestPage = state.closestPageToPosition(anchorPosition) ?: return null
        return closestPage.prevKey?.plus(1) ?: closestPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WatchListDEntity> {
        return when (val result = source.load(params)) {
            is LoadResult.Page -> LoadResult.Page(
                data = result.data.map { it.toDomain() },
                prevKey = result.prevKey,
                nextKey = result.nextKey
            )
            is LoadResult.Error -> LoadResult.Error(result.throwable)
            is LoadResult.Invalid -> LoadResult.Invalid()
        }
    }
}