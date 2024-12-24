package ir.androidcoder.traktmovies.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.data.remote.TMDBApiService
import ir.androidcoder.data.remote.TraktApiService
import ir.androidcoder.data.remote.interceptor.HeaderInterceptor
import ir.androidcoder.data.remote.interceptor.MoviesHeaderInterceptor
import ir.androidcoder.traktmovies.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @Named("trakt_api")
    fun provideOkHttpClient(headerInterceptor: HeaderInterceptor): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .build()


    @Provides
    @Singleton
    @Named("trakt_api")
    fun provideTraktRetrofit(@Named("trakt_api") okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_Trakt)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideTraktApiService(@Named("trakt_api") retrofit: Retrofit): TraktApiService = retrofit.create(TraktApiService::class.java)


    @Provides
    @Singleton
    @Named("TMDB_api")
    fun provideMoviesOkHttpClient(headerInterceptor: MoviesHeaderInterceptor): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .build()


    @Provides
    @Singleton
    @Named("TMDB_api")
    fun provideTMDBRetrofit(@Named("TMDB_api") okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_TMDB)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideTMDBApiService(@Named("TMDB_api") retrofit: Retrofit): TMDBApiService = retrofit.create(TMDBApiService::class.java)

}