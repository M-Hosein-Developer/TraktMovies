package ir.androidcoder.data.repository

import androidx.paging.Pager
import ir.androidcoder.data.source.MoviesSource
import ir.androidcoder.domain.entities.MovieDetailEntity
import ir.androidcoder.domain.entities.MoviesEntity
import ir.androidcoder.domain.entities.YoutubeEntity
import ir.androidcoder.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val source: MoviesSource) : MoviesRepository{

    override fun allNowPlaying(auth: String): Pager<Int, MoviesEntity> = source.allNowPlaying(auth)

    override fun allPopular(auth: String): Pager<Int, MoviesEntity> = source.allPopular(auth)

    override fun topRate(auth: String): Pager<Int, MoviesEntity> = source.allTopRate(auth)

    override fun upcoming(auth: String): Pager<Int, MoviesEntity> = source.allUpcoming(auth)

    override suspend fun movieDetail(id: Int, auth: String): MovieDetailEntity? = source.getMovieDetail(id , auth)

    override suspend fun getYoutubeVideo(id: Int, auth: String): List<YoutubeEntity>? = source.getYoutubeVideo(id , auth)

}