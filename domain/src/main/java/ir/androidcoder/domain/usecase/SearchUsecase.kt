package ir.androidcoder.domain.usecase

import androidx.paging.Pager
import ir.androidcoder.domain.entities.SearchEntity
import ir.androidcoder.domain.repository.SearchRepository

class SearchUsecase (private val repository: SearchRepository) {

    fun searchMovies(query: String) : Pager<Int, SearchEntity> = repository.searchMovies(query)

}