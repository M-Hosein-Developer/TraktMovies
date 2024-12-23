package ir.androidcoder.domain.usecase

import androidx.paging.Pager
import ir.androidcoder.domain.entities.MoviesEntity
import ir.androidcoder.domain.repository.MoviesRepository

class MoviesUsecase(private val repository: MoviesRepository) {

    fun allNowPlaying(auth : String) : Pager<Int, MoviesEntity> = repository.allNowPlaying(auth)

    fun allPopular(auth: String) : Pager<Int , MoviesEntity> = repository.allPopular(auth)

    fun allTopRate(auth: String): Pager<Int, MoviesEntity> = repository.topRate(auth)

    fun allUpcoming(auth: String): Pager<Int, MoviesEntity> = repository.upcoming(auth)

    suspend fun getMovieDetail(id : Int , auth: String) = repository.movieDetail(id , auth)

    suspend fun getYoutubeVideo(id: Int , auth: String) = repository.getYoutubeVideo(id , auth)

}