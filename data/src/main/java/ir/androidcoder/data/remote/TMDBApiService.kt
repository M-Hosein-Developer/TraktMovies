package ir.androidcoder.data.remote

import ir.androidcoder.data.model.NowPlayingResponse
import ir.androidcoder.data.model.PopularResponse
import ir.androidcoder.data.model.TopRatedResponse
import ir.androidcoder.data.model.UpcomingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TMDBApiService {


    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Header("Authorization") authorization: String
    ): Response<NowPlayingResponse>


    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Header("Authorization") authorization: String
    ): Response<PopularResponse>


    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Header("accept") accept: String = "application/json",
        @Header("Authorization") authorization: String
    ): TopRatedResponse


    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Header("Authorization") authorization: String
    ): UpcomingResponse

}