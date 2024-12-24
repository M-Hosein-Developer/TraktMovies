package ir.androidcoder.data.repository

import androidx.paging.Pager
import ir.androidcoder.data.source.SearchSource
import ir.androidcoder.domain.entities.SearchEntity
import ir.androidcoder.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val source: SearchSource) : SearchRepository {


    override fun searchMovies(query: String): Pager<Int, SearchEntity> = source.searchMovies(query)


}