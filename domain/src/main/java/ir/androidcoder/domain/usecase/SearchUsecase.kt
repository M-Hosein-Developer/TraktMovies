package ir.androidcoder.domain.usecase

import androidx.paging.Pager
import ir.androidcoder.domain.entities.SearchEntity
import ir.androidcoder.domain.repository.SearchRepository

class SearchUsecase (private val repository: SearchRepository) {

    fun searchMovies(auth : String , query: String) : Pager<Int, SearchEntity.Result> = repository.searchMovies(auth , query)

}