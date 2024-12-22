package ir.androidcoder.data.repository

import androidx.paging.Pager
import ir.androidcoder.data.source.MoviesSource
import ir.androidcoder.domain.entities.MovieDetailEntity
import ir.androidcoder.domain.entities.NowPlayingDEntity
import ir.androidcoder.domain.entities.PopularDEntity
import ir.androidcoder.domain.entities.TopRateDEntity
import ir.androidcoder.domain.entities.UpcomingDEntity
import ir.androidcoder.domain.entities.YoutubeEntity
import ir.androidcoder.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val source: MoviesSource) : MoviesRepository{

    override fun allNowPlaying(auth: String): Pager<Int, NowPlayingDEntity> = source.allNowPlaying(auth)

    override fun allPopular(auth: String): Pager<Int, PopularDEntity> = source.allPopular(auth)

    override fun topRate(auth: String): Pager<Int, TopRateDEntity> = source.allTopRate(auth)

    override fun upcoming(auth: String): Pager<Int, UpcomingDEntity> = source.allUpcoming(auth)

    override suspend fun movieDetail(id: Int, auth: String): MovieDetailEntity? = source.getMovieDetail(id , auth)

    override suspend fun getYoutubeVideo(id: Int, auth: String): List<YoutubeEntity>? = source.getYoutubeVideo(id , auth)

}