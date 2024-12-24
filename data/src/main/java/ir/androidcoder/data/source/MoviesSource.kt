package ir.androidcoder.data.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import ir.androidcoder.data.local.MoviesDao
import ir.androidcoder.data.mapper.toDomain
import ir.androidcoder.data.paging.NowPlayingMediator
import ir.androidcoder.data.paging.NowPlayingPagerSource
import ir.androidcoder.data.paging.PopularMediator
import ir.androidcoder.data.paging.PopularPagerSource
import ir.androidcoder.data.paging.TopRateMediator
import ir.androidcoder.data.paging.TopRatePagerSource
import ir.androidcoder.data.paging.UpcomingMediator
import ir.androidcoder.data.paging.UpcomingPagerSource
import ir.androidcoder.data.remote.TMDBApiService
import ir.androidcoder.domain.entities.MovieDetailEntity
import ir.androidcoder.domain.entities.MoviesEntity
import ir.androidcoder.domain.entities.YoutubeEntity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MoviesSource @Inject constructor(private val apiService: TMDBApiService , private val dao: MoviesDao) {

    fun allNowPlaying() : Pager<Int , MoviesEntity> = Pager(
        config = PagingConfig(6 , enablePlaceholders = false),
        remoteMediator = NowPlayingMediator(apiService , dao),
        pagingSourceFactory = { NowPlayingPagerSource(dao.getAllNowPlaying()) }
    )

    fun allPopular() : Pager<Int , MoviesEntity> = Pager(
        config = PagingConfig(6 , enablePlaceholders = false),
        remoteMediator = PopularMediator(apiService , dao),
        pagingSourceFactory = { PopularPagerSource(dao.getAllPopular()) }
    )

    fun allTopRate() : Pager<Int , MoviesEntity> = Pager(
        config = PagingConfig(6 , enablePlaceholders = false),
        remoteMediator = TopRateMediator(apiService , dao),
        pagingSourceFactory = { TopRatePagerSource(dao.getAllTopRate()) }
    )

    fun allUpcoming() : Pager<Int , MoviesEntity> = Pager(
        config = PagingConfig(6 , enablePlaceholders = false),
        remoteMediator = UpcomingMediator(apiService , dao),
        pagingSourceFactory = { UpcomingPagerSource(dao.getAllUpcoming()) }
    )

    suspend fun getMovieDetail(id : Int) : MovieDetailEntity? = apiService.getMoviesDetail(id).body()?.toDomain()

    suspend fun getYoutubeVideo(id: Int) : List<YoutubeEntity>? = apiService.getYoutubeVideo(id).body()?.results?.map { it.toDomain() }

}