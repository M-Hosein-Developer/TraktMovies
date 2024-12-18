package ir.androidcoder.data.mapper

import ir.androidcoder.data.local.entity.UpcomingEntity
import ir.androidcoder.data.model.UpcomingResponse
import ir.androidcoder.domain.entities.UpcomingDEntity

fun UpcomingResponse.Result.toDB() : UpcomingEntity = UpcomingEntity(
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

fun UpcomingEntity.toDomain() : UpcomingDEntity = UpcomingDEntity(
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

fun UpcomingDEntity.toDb() : UpcomingEntity = UpcomingEntity(
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