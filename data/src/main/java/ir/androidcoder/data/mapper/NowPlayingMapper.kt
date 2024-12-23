package ir.androidcoder.data.mapper

import ir.androidcoder.data.local.entity.NowPlayingEntity
import ir.androidcoder.data.model.NowPlayingResponse
import ir.androidcoder.domain.entities.MoviesEntity

fun NowPlayingResponse.Result.toDB() : NowPlayingEntity = NowPlayingEntity(
    id = id,
    adult = adult ,
    backdropPath = backdrop_path,
    originalLanguage = original_language,
    originalTitle = original_title ,
    overview = overview ,
    popularity = popularity,
    posterPath = poster_path ,
    releaseDate = release_date,
    title = title ,
    video = video ,
    voteAverage = vote_average,
    voteCount = vote_count
)

fun NowPlayingEntity.toDomain() : MoviesEntity = MoviesEntity(
    id = id,
    adult = adult ,
    backdropPath = backdropPath ,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun MoviesEntity.toDb() : NowPlayingEntity = NowPlayingEntity(
    id = id ,
    adult = adult ,
    backdropPath = backdropPath,
    originalLanguage = originalLanguage ,
    originalTitle = originalTitle ,
    overview = overview ,
    popularity = popularity ,
    posterPath = posterPath,
    releaseDate = releaseDate ,
    title = title ,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)

