package ir.androidcoder.data.remote

import ir.androidcoder.data.model.MovieDetailResponse
import ir.androidcoder.data.model.NowPlayingResponse
import ir.androidcoder.data.model.PopularResponse
import ir.androidcoder.data.model.SearchResponse
import ir.androidcoder.data.model.TopRatedResponse
import ir.androidcoder.data.model.UpcomingResponse
import ir.androidcoder.data.model.YoutubeResponse
import ir.androidcoder.data.model.adapter.GenericResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Response<NowPlayingResponse>


    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<PopularResponse>


    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<TopRatedResponse>


    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<UpcomingResponse>

    @GET("movie/{movie_id}?language=en-US")
    suspend fun getMoviesDetail(
        @Path("movie_id") movieId: Int
    ) : Response<MovieDetailResponse>

    @GET("movie/{movie_id}/videos?language=en-US")
    suspend fun getYoutubeVideo(
        @Path("movie_id") movieId: Int
    ) : Response<YoutubeResponse>

    @GET("search/collection?")
    suspend fun searchCollection(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): GenericResponse<SearchResponse>

}