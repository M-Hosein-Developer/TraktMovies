package ir.androidcoder.data.mapper

import ir.androidcoder.data.model.YoutubeResponse
import ir.androidcoder.domain.entities.YoutubeEntity

fun YoutubeResponse.Result.toDomain() : YoutubeEntity = YoutubeEntity(
    id = id,
    iso_639_1 = iso_639_1 ,
    iso_3166_1 = iso_3166_1 ,
    key = key ,
    name = name ,
    official = official ,
    published_at = published_at,
    site = site ,
    size = size ,
    type = type
)