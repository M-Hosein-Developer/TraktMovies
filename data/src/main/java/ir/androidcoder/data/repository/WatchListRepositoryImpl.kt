package ir.androidcoder.data.repository

import androidx.paging.Pager
import ir.androidcoder.data.source.WatchListSource
import ir.androidcoder.domain.entities.WatchListDEntity
import ir.androidcoder.domain.repository.WatchListRepository
import javax.inject.Inject

class WatchListRepositoryImpl @Inject constructor(private val source : WatchListSource) : WatchListRepository {

    override suspend fun insertWatchList(movies: WatchListDEntity) {
        source.insertWatchList(movies)
    }

    override fun getAllWatchList(): Pager<Int, WatchListDEntity> = source.getAllWatchList()

    override fun getWatchlistBySearch(query: String): Pager<Int, WatchListDEntity> = source.getWatchlistBySearch(query)

}