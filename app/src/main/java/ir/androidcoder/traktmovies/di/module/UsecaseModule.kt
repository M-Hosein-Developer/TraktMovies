package ir.androidcoder.traktmovies.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.domain.repository.AuthRepository
import ir.androidcoder.domain.repository.MoviesRepository
import ir.androidcoder.domain.usecase.AuthUsecase
import ir.androidcoder.domain.usecase.MoviesUsecase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UsecaseModule {

    @Provides
    @Singleton
    fun provideAuthUsecase(repository: AuthRepository) : AuthUsecase = AuthUsecase(repository)

    @Provides
    @Singleton
    fun provideMoviesUsecase(repository: MoviesRepository) : MoviesUsecase = MoviesUsecase(repository)

}