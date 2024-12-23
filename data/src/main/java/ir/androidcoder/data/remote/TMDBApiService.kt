package ir.androidcoder.data.remote

import ir.androidcoder.data.model.MovieDetailResponse
import ir.androidcoder.data.model.NowPlayingResponse
import ir.androidcoder.data.model.PopularResponse
import ir.androidcoder.data.model.SearchResponse
import ir.androidcoder.data.model.TopRatedResponse
import ir.androidcoder.data.model.UpcomingResponse
import ir.androidcoder.data.model.YoutubeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApiService {

    @GET("movie/now_playing")
    @Headers("accept: application/json")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
        @Header("Authorization") authorization: String
    ): Response<NowPlayingResponse>


    @GET("movie/popular")
    @Headers("accept: application/json")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Header("Authorization") authorization: String
    ): Response<PopularResponse>


    @GET("movie/top_rated")
    @Headers("accept: application/json")
    suspend fun getTopRatedMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Header("accept") accept: String = "application/json",
        @Header("Authorization") authorization: String
    ): Response<TopRatedResponse>


    @GET("movie/upcoming")
    @Headers("accept: application/json")
    suspend fun getUpcomingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Header("Authorization") authorization: String
    ): Response<UpcomingResponse>

    @GET("movie/{movie_id}?language=en-US")
    @Headers("accept: application/json")
    suspend fun getMoviesDetail(
        @Path("movie_id") movieId: Int,
        @Header("Authorization") authorization: String
    ) : Response<MovieDetailResponse>

    @GET("movie/{movie_id}/videos?language=en-US")
    @Headers("accept: application/json")
    suspend fun getYoutubeVideo(
        @Path("movie_id") movieId: Int,
        @Header("Authorization") authorization: String
    ) : Response<YoutubeResponse>

    @GET("search/collection")
    @Headers("accept: application/json")
    fun searchCollection(
        @Header("Authorization") authorization: String,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<SearchResponse>

}