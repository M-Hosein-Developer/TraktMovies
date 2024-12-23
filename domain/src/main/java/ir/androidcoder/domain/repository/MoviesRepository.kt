package ir.androidcoder.domain.repository

import androidx.paging.Pager
import ir.androidcoder.domain.entities.MovieDetailEntity
import ir.androidcoder.domain.entities.MoviesEntity
import ir.androidcoder.domain.entities.YoutubeEntity

interface MoviesRepository {

    fun allNowPlaying(auth : String) : Pager<Int, MoviesEntity>

    fun allPopular(auth : String) : Pager<Int, MoviesEntity>

    fun topRate(auth : String) : Pager<Int, MoviesEntity>

    fun upcoming(auth : String) : Pager<Int, MoviesEntity>

    suspend fun movieDetail(id : Int , auth : String) : MovieDetailEntity?

    suspend fun getYoutubeVideo(id: Int, auth: String) : List<YoutubeEntity>?
}