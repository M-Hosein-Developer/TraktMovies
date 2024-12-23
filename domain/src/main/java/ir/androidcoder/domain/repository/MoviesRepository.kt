package ir.androidcoder.domain.repository

import androidx.paging.Pager
import ir.androidcoder.domain.entities.MovieDetailEntity
import ir.androidcoder.domain.entities.MoviesEntity
import ir.androidcoder.domain.entities.YoutubeEntity

interface MoviesRepository {

    fun allNowPlaying() : Pager<Int, MoviesEntity>

    fun allPopular() : Pager<Int, MoviesEntity>

    fun topRate() : Pager<Int, MoviesEntity>

    fun upcoming() : Pager<Int, MoviesEntity>

    suspend fun movieDetail(id : Int) : MovieDetailEntity?

    suspend fun getYoutubeVideo(id: Int) : List<YoutubeEntity>?
}