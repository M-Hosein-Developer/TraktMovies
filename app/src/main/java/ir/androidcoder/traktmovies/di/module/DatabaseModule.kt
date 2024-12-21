package ir.androidcoder.traktmovies.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.androidcoder.data.local.MoviesDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDb(@ApplicationContext context: Context) : MoviesDatabase = Room.databaseBuilder(
        context,
        MoviesDatabase::class.java,
        "moviesDb.db"
    )
//        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideMoviesDao(moviesDatabase: MoviesDatabase) = moviesDatabase.MoviesDao()

}