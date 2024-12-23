package ir.androidcoder.data.mapper

import ir.androidcoder.data.model.SearchResponse
import ir.androidcoder.domain.entities.SearchEntity

fun SearchResponse.toDomain() : SearchEntity = SearchEntity(
    page = page,
    results = results.map { it.toDomain() },
    total_results = total_results,
    total_pages = total_pages
)

fun SearchResponse.Result.toDomain() : SearchEntity.Result = SearchEntity.Result(
    adult = adult,
    backdrop_path = backdrop_path,
    id = id,
    name = name,
    original_language = original_language,
    original_name = original_name,
    overview = overview,
    poster_path = poster_path
)