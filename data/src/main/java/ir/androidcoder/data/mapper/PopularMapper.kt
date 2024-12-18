package ir.androidcoder.data.mapper

import ir.androidcoder.data.local.entity.PopularEntity
import ir.androidcoder.data.model.PopularResponse
import ir.androidcoder.domain.entities.PopularDEntity

fun PopularResponse.Result.toDB() : PopularEntity = PopularEntity(
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

fun PopularEntity.toDomain() : PopularDEntity = PopularDEntity(
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

fun PopularDEntity.toDb() : PopularEntity = PopularEntity(
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