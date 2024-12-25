package ir.androidcoder.data.mapper

import ir.androidcoder.data.local.entity.WatchListEntity
import ir.androidcoder.domain.entities.WatchListDEntity

fun WatchListEntity.toDomain() : WatchListDEntity = WatchListDEntity(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    posterPath = posterPath,
    popularity = popularity,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteCount = voteCount,
    voteAverage = voteAverage
)



fun WatchListDEntity.toData() : WatchListEntity = WatchListEntity(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    posterPath = posterPath,
    popularity = popularity,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteCount = voteCount,
    voteAverage = voteAverage
)