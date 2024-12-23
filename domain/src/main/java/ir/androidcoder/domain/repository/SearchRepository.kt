package ir.androidcoder.domain.repository

import androidx.paging.Pager
import ir.androidcoder.domain.entities.SearchEntity

interface SearchRepository {

    fun searchMovies(auth : String , query: String) : Pager<Int, SearchEntity.Result>

}