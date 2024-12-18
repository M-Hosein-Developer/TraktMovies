package ir.androidcoder.data.repository

import androidx.paging.Pager
import ir.androidcoder.data.source.MoviesSource
import ir.androidcoder.domain.entities.NowPlayingDEntity
import ir.androidcoder.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val source: MoviesSource) : MoviesRepository{

    override fun allNowPlaying(auth: String): Pager<Int, NowPlayingDEntity> = source.allNowPlaying(auth)


}