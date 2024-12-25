package ir.androidcoder.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import ir.androidcoder.data.local.MoviesDao
import ir.androidcoder.data.mapper.toData
import ir.androidcoder.data.paging.watchList.WatchListConverterSource
import ir.androidcoder.data.paging.watchList.WatchListPagingSource
import ir.androidcoder.domain.entities.WatchListDEntity
import javax.inject.Inject

class WatchListSource @Inject constructor(private val dao: MoviesDao) {

    suspend fun insertWatchList(movies : WatchListDEntity){
        dao.insertWatchList(movies.toData())
    }

    fun getAllWatchList() : Pager<Int , WatchListDEntity> = Pager(
        config = PagingConfig(pageSize = 10 , enablePlaceholders = false),
        pagingSourceFactory = { WatchListConverterSource(dao.getAllWatchList()) }
    )

    fun getWatchlistBySearch(query : String) : Pager<Int , WatchListDEntity> = Pager(
        config = PagingConfig(pageSize = 10 , enablePlaceholders = false),
        pagingSourceFactory = {WatchListPagingSource(dao , query)}
    )

}