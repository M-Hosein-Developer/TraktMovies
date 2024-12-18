package ir.androidcoder.traktmovies.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UntilModule {

    @Provides
    fun provideContext(@ApplicationContext context : Context) : Context = context.applicationContext

}