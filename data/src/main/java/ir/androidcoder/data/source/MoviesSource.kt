package ir.androidcoder.data.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import ir.androidcoder.data.local.MoviesDao
import ir.androidcoder.data.paging.NowPlayingMediator
import ir.androidcoder.data.paging.NowPlayingPagerSource
import ir.androidcoder.data.paging.PopularMediator
import ir.androidcoder.data.paging.PopularPagerSource
import ir.androidcoder.data.paging.TopRateMediator
import ir.androidcoder.data.paging.TopRatePagerSource
import ir.androidcoder.data.paging.UpcomingMediator
import ir.androidcoder.data.paging.UpcomingPagerSource
import ir.androidcoder.data.remote.TMDBApiService
import ir.androidcoder.domain.entities.NowPlayingDEntity
import ir.androidcoder.domain.entities.PopularDEntity
import ir.androidcoder.domain.entities.TopRateDEntity
import ir.androidcoder.domain.entities.UpcomingDEntity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MoviesSource @Inject constructor(private val apiService: TMDBApiService , private val dao: MoviesDao) {

    fun allNowPlaying(auth : String) : Pager<Int , NowPlayingDEntity> = Pager(
        config = PagingConfig(7 , enablePlaceholders = false),
        remoteMediator = NowPlayingMediator(apiService , dao , auth),
        pagingSourceFactory = { NowPlayingPagerSource(dao.getAllNowPlaying()) }
    )

    fun allPopular(auth: String) : Pager<Int , PopularDEntity> = Pager(
        config = PagingConfig(7 , enablePlaceholders = false),
        remoteMediator = PopularMediator(apiService , dao , auth),
        pagingSourceFactory = { PopularPagerSource(dao.getAllPopular()) }
    )

    fun allTopRate(auth: String) : Pager<Int , TopRateDEntity> = Pager(
        config = PagingConfig(7 , enablePlaceholders = false),
        remoteMediator = TopRateMediator(apiService , dao , auth),
        pagingSourceFactory = { TopRatePagerSource(dao.getAllTopRate()) }
    )

    fun allUpcoming(auth: String) : Pager<Int , UpcomingDEntity> = Pager(
        config = PagingConfig(7 , enablePlaceholders = false),
        remoteMediator = UpcomingMediator(apiService , dao , auth),
        pagingSourceFactory = { UpcomingPagerSource(dao.getAllUpcoming()) }
    )

}