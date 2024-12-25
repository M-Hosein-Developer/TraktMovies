package ir.androidcoder.domain.repository

import androidx.paging.Pager
import ir.androidcoder.domain.entities.WatchListDEntity

interface WatchListRepository {

    suspend fun insertWatchList(movies : WatchListDEntity)

    fun getAllWatchList() : Pager<Int, WatchListDEntity>

    fun getWatchlistBySearch(query : String) : Pager<Int , WatchListDEntity>

}