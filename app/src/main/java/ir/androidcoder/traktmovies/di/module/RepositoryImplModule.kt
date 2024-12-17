package ir.androidcoder.traktmovies.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.data.repository.AuthRepositoryImpl
import ir.androidcoder.data.source.AuthSource

@Module
@InstallIn(SingletonComponent::class)
class RepositoryImplModule {

    @Provides
    fun provideAuthRepoImpl(authSource: AuthSource) : AuthRepositoryImpl = AuthRepositoryImpl(authSource)

}