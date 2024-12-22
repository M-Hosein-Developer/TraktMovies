package ir.androidcoder.data.remote

import ir.androidcoder.data.model.MovieDetailResponse
import ir.androidcoder.data.model.NowPlayingResponse
import ir.androidcoder.data.model.PopularResponse
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

    @GET("now_playing")
    @Headers("accept: application/json")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
        @Header("Authorization") authorization: String
    ): Response<NowPlayingResponse>


    @GET("popular")
    @Headers("accept: application/json")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Header("Authorization") authorization: String
    ): Response<PopularResponse>


    @GET("top_rated")
    @Headers("accept: application/json")
    suspend fun getTopRatedMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Header("accept") accept: String = "application/json",
        @Header("Authorization") authorization: String
    ): Response<TopRatedResponse>


    @GET("upcoming")
    @Headers("accept: application/json")
    suspend fun getUpcomingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Header("Authorization") authorization: String
    ): Response<UpcomingResponse>

    @GET("{movie_id}?language=en-US")
    @Headers("accept: application/json")
    suspend fun getMoviesDetail(
        @Path("movie_id") movieId: Int,
        @Header("Authorization") authorization: String
    ) : Response<MovieDetailResponse>

    @GET("{movie_id}/videos?language=en-US")
    @Headers("accept: application/json")
    suspend fun getYoutubeVideo(
        @Path("movie_id") movieId: Int,
        @Header("Authorization") authorization: String
    ) : Response<YoutubeResponse>

}