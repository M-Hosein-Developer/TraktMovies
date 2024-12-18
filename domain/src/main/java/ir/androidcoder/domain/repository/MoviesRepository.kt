package ir.androidcoder.domain.repository

import androidx.paging.Pager
import ir.androidcoder.domain.entities.NowPlayingDEntity

interface MoviesRepository {

    fun allNowPlaying(auth : String) : Pager<Int, NowPlayingDEntity>

}