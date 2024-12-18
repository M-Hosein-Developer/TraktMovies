package ir.androidcoder.traktmovies.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.data.local.MoviesDao
import ir.androidcoder.data.remote.TMDBApiService
import ir.androidcoder.data.remote.TraktApiService
import ir.androidcoder.data.source.AuthSource
import ir.androidcoder.data.source.MoviesSource

@Module
@InstallIn(SingletonComponent::class)
class SourceModule {

    @Provides
    fun provideAuthSource(apiService: TraktApiService) : AuthSource = AuthSource(apiService)

    @Provides
    fun provideMoviesSource(apiService: TMDBApiService , moviesDao: MoviesDao) : MoviesSource = MoviesSource(apiService , moviesDao)

}