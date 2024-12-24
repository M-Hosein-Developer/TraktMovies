package ir.androidcoder.data.mapper

import android.util.Log
import ir.androidcoder.data.model.SearchResponse
import ir.androidcoder.domain.entities.SearchEntity

fun SearchResponse.Result.toDomain(): SearchEntity {
    Log.v("ToDomain", "Converting: $this")
    return SearchEntity(
        adult = adult,
        backdropPath = backdrop_path ?: "",
        id = id,
        name = name,
        originalLanguage = original_language,
        originalName = original_name,
        overview = overview,
        posterPath = poster_path ?: ""
    )
}