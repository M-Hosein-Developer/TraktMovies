package ir.androidcoder.traktmovies.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.data.repository.AuthRepositoryImpl
import ir.androidcoder.data.repository.MoviesRepositoryImpl
import ir.androidcoder.data.repository.SearchRepositoryImpl
import ir.androidcoder.domain.repository.AuthRepository
import ir.androidcoder.domain.repository.MoviesRepository
import ir.androidcoder.domain.repository.SearchRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class IRepositoryModule {

    @Binds
    abstract fun provideAuthRepository(repo : AuthRepositoryImpl) : AuthRepository

    @Binds
    abstract fun provideMoviesRepository(repo : MoviesRepositoryImpl) : MoviesRepository

    @Binds
    abstract fun provideSearchRepository(repo : SearchRepositoryImpl) : SearchRepository

}