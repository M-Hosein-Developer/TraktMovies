package ir.androidcoder.data.mapper

import androidx.paging.PagingSource
import ir.androidcoder.data.local.entity.NowPlayingEntity
import ir.androidcoder.data.model.NowPlayingResponse
import ir.androidcoder.domain.entities.NowPlayingDEntity

fun NowPlayingResponse.Result.toDB() : NowPlayingEntity = NowPlayingEntity(
    id = id,
    adult = adult,
    backdrop_path = backdrop_path,
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    popularity = popularity,
    poster_path = poster_path,
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)

fun NowPlayingEntity.toDomain() : NowPlayingDEntity = NowPlayingDEntity(
    id = id,
    adult = adult,
    backdrop_path = backdrop_path ,
    original_title = original_title,
    original_language = original_language,
    overview = overview,
    popularity = popularity,
    poster_path = poster_path,
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)

fun NowPlayingDEntity.toDb() : NowPlayingEntity = NowPlayingEntity(
    id = id,
    adult = adult,
    backdrop_path = backdrop_path ,
    original_title = original_title,
    original_language = original_language,
    overview = overview,
    popularity = popularity,
    poster_path = poster_path,
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)