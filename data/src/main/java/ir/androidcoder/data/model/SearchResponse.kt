package ir.androidcoder.data.model

data class SearchResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
) {
    data class Result(
        val adult: Boolean,
        val backdrop_path: String,
        val id: Int,
        val name: String,
        val original_language: String,
        val original_name: String,
        val overview: String,
        val poster_path: String
    )
}