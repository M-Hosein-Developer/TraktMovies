package ir.androidcoder.domain.entities

data class SearchEntity(
    val page: Int,
    val results: List<Result>,
    val totalPages: Int,
    val totalResults: Int
) {
    data class Result(
        val adult: Boolean,
        val backdropPath: String,
        val id: Int,
        val name: String,
        val originalLanguage: String,
        val originalName: String,
        val overview: String,
        val posterPath: String
    )
}