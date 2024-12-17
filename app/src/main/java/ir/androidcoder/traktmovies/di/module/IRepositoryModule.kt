package ir.androidcoder.traktmovies.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.data.repository.AuthRepositoryImpl
import ir.androidcoder.domain.repository.AuthRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class IRepositoryModule {

    @Binds
    abstract fun provideAuthRepository(repo : AuthRepositoryImpl) : AuthRepository

}