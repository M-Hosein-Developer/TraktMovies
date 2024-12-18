package ir.androidcoder.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.androidcoder.data.local.entity.UpcomingEntity
import ir.androidcoder.data.mapper.toDomain
import ir.androidcoder.domain.entities.UpcomingDEntity

class UpcomingPagerSource(private val source: PagingSource<Int, UpcomingEntity>) : PagingSource<Int, UpcomingDEntity>() {

    override fun getRefreshKey(state: PagingState<Int, UpcomingDEntity>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val closestPage = state.closestPageToPosition(anchorPosition) ?: return null
        return closestPage.prevKey?.plus(1) ?: closestPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UpcomingDEntity> {
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