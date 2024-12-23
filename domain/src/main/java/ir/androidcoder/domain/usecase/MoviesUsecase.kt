package ir.androidcoder.domain.usecase

import androidx.paging.Pager
import ir.androidcoder.domain.entities.MoviesEntity
import ir.androidcoder.domain.repository.MoviesRepository

class MoviesUsecase(private val repository: MoviesRepository) {

    fun allNowPlaying() : Pager<Int, MoviesEntity> = repository.allNowPlaying()

    fun allPopular() : Pager<Int , MoviesEntity> = repository.allPopular()

    fun allTopRate(): Pager<Int, MoviesEntity> = repository.topRate()

    fun allUpcoming(): Pager<Int, MoviesEntity> = repository.upcoming()

    suspend fun getMovieDetail(id : Int) = repository.movieDetail(id)

    suspend fun getYoutubeVideo(id: Int) = repository.getYoutubeVideo(id)

}