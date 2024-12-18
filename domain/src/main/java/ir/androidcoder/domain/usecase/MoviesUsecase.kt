package ir.androidcoder.domain.usecase

import androidx.paging.Pager
import ir.androidcoder.domain.entities.NowPlayingDEntity
import ir.androidcoder.domain.entities.PopularDEntity
import ir.androidcoder.domain.repository.MoviesRepository

class MoviesUsecase(private val repository: MoviesRepository) {

    fun allNowPlaying(auth : String) : Pager<Int, NowPlayingDEntity> = repository.allNowPlaying(auth)

    fun allPopular(auth: String) : Pager<Int , PopularDEntity> = repository.allPopular(auth)

}