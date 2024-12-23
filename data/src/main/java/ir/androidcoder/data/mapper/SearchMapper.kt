package ir.androidcoder.data.mapper

import ir.androidcoder.data.model.SearchResponse
import ir.androidcoder.domain.entities.SearchEntity

fun SearchResponse.toDomain() : SearchEntity = SearchEntity(
    page = page,
    results = results.map { it.toDomain() },
    totalResults = total_results ,
    totalPages = total_pages
)

fun SearchResponse.Result.toDomain() : SearchEntity.Result = SearchEntity.Result(
    adult = adult ,
    backdropPath = backdrop_path ,
    id = id ,
    name = name ,
    originalLanguage = original_language ,
    originalName = original_name ,
    overview = overview,
    posterPath = poster_path
)