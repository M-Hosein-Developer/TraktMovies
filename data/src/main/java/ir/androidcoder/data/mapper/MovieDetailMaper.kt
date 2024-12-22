package ir.androidcoder.data.mapper

import ir.androidcoder.data.model.MovieDetailResponse
import ir.androidcoder.domain.entities.MovieDetailEntity

fun MovieDetailResponse.toDomain() : MovieDetailEntity = MovieDetailEntity(

    adult = adult ?: false,
    backdrop_path = backdrop_path ?: "",
    belongs_to_collection = belongs_to_collection ?: "",
    budget = budget ?: 0,
    genres = genres.map { it.toDomain() },
    homepage = homepage ?: "",
    id = id,
    imdb_id = imdb_id ?: "",
    origin_country = origin_country,
    original_language = original_language ?: "",
    original_title = original_title ?: "",
    overview = overview ?: "",
    popularity = popularity ?: 0.0,
    poster_path = poster_path ?: "",
    production_companies = production_companies.map { it.toDomain() },
    production_countries = production_countries.map { it.toDomain() },
    release_date = release_date ?: "",
    revenue = revenue ?: 0,
    runtime = runtime ?: 0,
    spoken_languages = spoken_languages.map { it.toDomain() },
    status = status ?: "",
    tagline = tagline ?: "",
    title = title ?: "",
    video = video ?: false,
    vote_average = vote_average ?: 0.0,
    vote_count = vote_count ?: 0
)

fun MovieDetailResponse.Genre.toDomain() : MovieDetailEntity.Genre = MovieDetailEntity.Genre(
    id = id ?: 0,
    name = name ?: ""
)

fun MovieDetailResponse.ProductionCompany.toDomain() : MovieDetailEntity.ProductionCompany = MovieDetailEntity.ProductionCompany(
    id = id ?: 0,
    logo_path = logo_path ?: "",
    name = name ?: "",
    origin_country = origin_country ?: ""
)

fun MovieDetailResponse.ProductionCountry.toDomain() : MovieDetailEntity.ProductionCountry = MovieDetailEntity.ProductionCountry(
    iso_3166_1 = iso_3166_1 ?: "",
    name = name ?: ""
)

fun MovieDetailResponse.SpokenLanguage.toDomain() : MovieDetailEntity.SpokenLanguage = MovieDetailEntity.SpokenLanguage(
    english_name = english_name ?: "",
    iso_639_1 = iso_639_1 ?: "",
    name = name ?: ""
)


