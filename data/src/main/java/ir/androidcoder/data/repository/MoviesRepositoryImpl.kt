package ir.androidcoder.data.repository

import androidx.paging.Pager
import ir.androidcoder.data.source.MoviesSource
import ir.androidcoder.domain.entities.MovieDetailEntity
import ir.androidcoder.domain.entities.MoviesEntity
import ir.androidcoder.domain.entities.YoutubeEntity
import ir.androidcoder.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val source: MoviesSource) : MoviesRepository{

    override fun allNowPlaying(): Pager<Int, MoviesEntity> = source.allNowPlaying()

    override fun allPopular(): Pager<Int, MoviesEntity> = source.allPopular()

    override fun topRate(): Pager<Int, MoviesEntity> = source.allTopRate()

    override fun upcoming(): Pager<Int, MoviesEntity> = source.allUpcoming()

    override suspend fun movieDetail(id: Int): MovieDetailEntity? = source.getMovieDetail(id)

    override suspend fun getYoutubeVideo(id: Int): List<YoutubeEntity>? = source.getYoutubeVideo(id)

}