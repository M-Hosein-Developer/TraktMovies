package ir.androidcoder.data.mapper

import ir.androidcoder.data.local.entity.UpcomingEntity
import ir.androidcoder.data.model.UpcomingResponse
import ir.androidcoder.domain.entities.MoviesEntity

fun UpcomingResponse.Result.toDB() : UpcomingEntity = UpcomingEntity(
    adult = adult,
    backdropPath = backdrop_path,
    id = id,
    originalLanguage = original_language ,
    originalTitle =original_title ,
    overview = overview ,
    popularity = popularity,
    posterPath = poster_path ,
    releaseDate = release_date ,
    title = title,
    video = video,
    voteAverage = vote_average,
    voteCount = vote_count
)

fun UpcomingEntity.toDomain() : MoviesEntity = MoviesEntity(
    adult = adult,
    backdropPath = backdropPath,
    id = id,
    originalLanguage = originalLanguage,
    originalTitle =originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)

/*fun MoviesEntity.toDb() : UpcomingEntity = UpcomingEntity(
    adult = adult,
    backdrop_path = backdropPath,
    id = id,
    original_language = originalLanguage,
    original_title = originalTitle,
    overview = overview,
    popularity = popularity,
    poster_path = posterPath,
    release_date = releaseDate,
    title = title,
    video = video,
    vote_average = voteAverage,
    vote_count = voteCount
)
 */