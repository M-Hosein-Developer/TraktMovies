package ir.androidcoder.domain.usecase

import androidx.paging.Pager
import ir.androidcoder.domain.entities.WatchListDEntity
import ir.androidcoder.domain.repository.WatchListRepository

class WatchListUsecase(private val repository: WatchListRepository) {

    suspend fun insertWatchList(movies: WatchListDEntity) {
        repository.insertWatchList(movies)
    }

    fun getAllWatchList(): Pager<Int, WatchListDEntity> = repository.getAllWatchList()

    fun getWatchlistBySearch(query: String): Pager<Int, WatchListDEntity> = repository.getWatchlistBySearch(query)

}