package ir.androidcoder.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.androidcoder.data.local.entity.PopularEntity
import ir.androidcoder.data.mapper.toDomain
import ir.androidcoder.domain.entities.PopularDEntity

class PopularPagerSource(private val source: PagingSource<Int, PopularEntity>
) : PagingSource<Int, PopularDEntity>() {

    override fun getRefreshKey(state: PagingState<Int, PopularDEntity>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val closestPage = state.closestPageToPosition(anchorPosition) ?: return null
        return closestPage.prevKey?.plus(1) ?: closestPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularDEntity> {
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