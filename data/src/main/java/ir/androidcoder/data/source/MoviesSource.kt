package ir.androidcoder.data.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import ir.androidcoder.data.local.MoviesDao
import ir.androidcoder.data.paging.NowPlayingMediator
import ir.androidcoder.data.paging.NowPlayingPagerSource
import ir.androidcoder.data.remote.TMDBApiService
import ir.androidcoder.domain.entities.NowPlayingDEntity
import javax.inject.Inject

class MoviesSource @Inject constructor(private val apiService: TMDBApiService , private val dao: MoviesDao) {

    @OptIn(ExperimentalPagingApi::class)
    fun allNowPlaying(auth : String) : Pager<Int , NowPlayingDEntity> = Pager(
        config = PagingConfig(7 , enablePlaceholders = false),
        remoteMediator = NowPlayingMediator(apiService , dao , auth),
        pagingSourceFactory = { NowPlayingPagerSource(dao.getAllNowPlaying()) }
    )

}