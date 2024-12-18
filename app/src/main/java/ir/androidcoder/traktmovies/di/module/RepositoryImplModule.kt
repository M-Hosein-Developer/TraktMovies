package ir.androidcoder.traktmovies.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.data.repository.AuthRepositoryImpl
import ir.androidcoder.data.repository.MoviesRepositoryImpl
import ir.androidcoder.data.source.AuthSource
import ir.androidcoder.data.source.MoviesSource

@Module
@InstallIn(SingletonComponent::class)
class RepositoryImplModule {

    @Provides
    fun provideAuthRepoImpl(authSource: AuthSource , context: Context) : AuthRepositoryImpl = AuthRepositoryImpl(authSource , context)

    @Provides
    fun provideMoviesImpl(moviesSource: MoviesSource) : MoviesRepositoryImpl = MoviesRepositoryImpl(moviesSource)

}