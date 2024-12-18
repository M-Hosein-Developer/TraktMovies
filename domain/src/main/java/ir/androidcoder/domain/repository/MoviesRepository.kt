package ir.androidcoder.domain.repository

import androidx.paging.Pager
import ir.androidcoder.domain.entities.NowPlayingDEntity
import ir.androidcoder.domain.entities.PopularDEntity

interface MoviesRepository {

    fun allNowPlaying(auth : String) : Pager<Int, NowPlayingDEntity>

    fun allPopular(auth : String) : Pager<Int, PopularDEntity>

}