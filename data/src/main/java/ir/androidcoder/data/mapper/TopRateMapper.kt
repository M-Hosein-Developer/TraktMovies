package ir.androidcoder.data.mapper

import ir.androidcoder.data.local.entity.TopRateEntity
import ir.androidcoder.data.model.TopRatedResponse
import ir.androidcoder.domain.entities.TopRateDEntity

fun TopRatedResponse.Result.toDB() : TopRateEntity = TopRateEntity(
    adult = adult,
    backdrop_path = backdrop_path,
    id = id,
    original_language = original_language,
    original_title =original_title,
    overview = overview,
    popularity = popularity,
    poster_path = poster_path,
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)

fun TopRateEntity.toDomain() : TopRateDEntity = TopRateDEntity(
    adult = adult,
    backdrop_path = backdrop_path,
    id = id,
    original_language = original_language,
    original_title =original_title,
    overview = overview,
    popularity = popularity,
    poster_path = poster_path,
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)

fun TopRateDEntity.toDb() : TopRateEntity = TopRateEntity(
    adult = adult,
    backdrop_path = backdrop_path,
    id = id,
    original_language = original_language,
    original_title =original_title,
    overview = overview,
    popularity = popularity,
    poster_path = poster_path,
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)