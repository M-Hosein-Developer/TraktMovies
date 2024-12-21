package ir.androidcoder.data.mapper

import ir.androidcoder.data.local.entity.NowPlayingEntity
import ir.androidcoder.data.model.NowPlayingResponse
import ir.androidcoder.domain.entities.NowPlayingDEntity

fun NowPlayingResponse.Result.toDB() : NowPlayingEntity = NowPlayingEntity(
    id = id,
    adult = adult ?: false,
    backdrop_path = backdrop_path ?: "",
    original_language = original_language ?: "",
    original_title = original_title ?: "",
    overview = overview ?: "",
    popularity = popularity ?: 0.0,
    poster_path = poster_path ?: "",
    release_date = release_date ?: "",
    title = title ?: "",
    video = video ?: false,
    vote_average = vote_average ?: 0.0,
    vote_count = vote_count ?: 0
)

fun NowPlayingEntity.toDomain() : NowPlayingDEntity = NowPlayingDEntity(
    id = id,
    adult = adult ?: false,
    backdrop_path = backdrop_path ?: "",
    original_language = original_language ?: "",
    original_title = original_title ?: "",
    overview = overview ?: "",
    popularity = popularity ?: 0.0,
    poster_path = poster_path ?: "",
    release_date = release_date ?: "",
    title = title ?: "",
    video = video ?: false,
    vote_average = vote_average ?: 0.0,
    vote_count = vote_count ?: 0
)

fun NowPlayingDEntity.toDb() : NowPlayingEntity = NowPlayingEntity(
    id = id,
    adult = adult ?: false,
    backdrop_path = backdrop_path ?: "",
    original_language = original_language ?: "",
    original_title = original_title ?: "",
    overview = overview ?: "",
    popularity = popularity ?: 0.0,
    poster_path = poster_path ?: "",
    release_date = release_date ?: "",
    title = title ?: "",
    video = video ?: false,
    vote_average = vote_average ?: 0.0,
    vote_count = vote_count ?: 0
)