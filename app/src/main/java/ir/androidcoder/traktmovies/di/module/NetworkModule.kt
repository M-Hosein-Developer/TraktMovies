package ir.androidcoder.traktmovies.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.data.remote.TMDBApiService
import ir.androidcoder.data.remote.TraktApiService
import ir.androidcoder.traktmovies.BuildConfig
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
    fun provideTraktRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL_Trakt)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideTraktApiService( @Named("trakt_api") retrofit: Retrofit) : TraktApiService = retrofit.create(TraktApiService::class.java)


    @Provides
    @Singleton
    @Named("TMDB_api")
    fun provideTMDBRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL_TMDB)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideTMDBApiService( @Named("TMDB_api") retrofit: Retrofit) : TMDBApiService = retrofit.create(TMDBApiService::class.java)


}