package ir.androidcoder.data.mapper

import ir.androidcoder.data.model.MovieDetailResponse
import ir.androidcoder.domain.entities.MovieDetailEntity

fun MovieDetailResponse.toDomain() : MovieDetailEntity = MovieDetailEntity(

    adult = adult,
    backdropPath = backdrop_path,
    belongsToCollection = belongs_to_collection ?: "",
    budget = budget,
    genres = genres.map { it.toDomain() },
    homepage = homepage ,
    id = id ,
    imdbId = imdb_id ,
    originCountry = origin_country,
    originalLanguage = original_language ,
    originalTitle = original_title ,
    overview = overview ,
    popularity = popularity ,
    posterPath = poster_path ,
    productionCompanies = production_companies.map { it.toDomain() } ,
    productionCountries = production_countries.map { it.toDomain() } ,
    releaseDate = release_date ,
    revenue = revenue ,
    runtime = runtime,
    spokenLanguages = spoken_languages.map { it.toDomain() } ,
    status = status ,
    tagline = tagline ,
    title = title ,
    video = video,
    voteAverage = vote_average,
    voteCount = vote_count
)

fun MovieDetailResponse.Genre.toDomain() : MovieDetailEntity.Genre = MovieDetailEntity.Genre(
    id = id ,
    name = name
)

fun MovieDetailResponse.ProductionCompany.toDomain() : MovieDetailEntity.ProductionCompany = MovieDetailEntity.ProductionCompany(
    id = id ,
    logoPath = logo_path ?: "" ,
    name = name,
    originCountry = origin_country
)

fun MovieDetailResponse.ProductionCountry.toDomain() : MovieDetailEntity.ProductionCountry = MovieDetailEntity.ProductionCountry(
    iso31661 = iso_3166_1,
    name = name
)

fun MovieDetailResponse.SpokenLanguage.toDomain() : MovieDetailEntity.SpokenLanguage = MovieDetailEntity.SpokenLanguage(
    englishName = english_name,
    iso_639_1 = iso_639_1,
    name = name
)