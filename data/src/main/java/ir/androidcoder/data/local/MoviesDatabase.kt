package ir.androidcoder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.androidcoder.data.local.entity.NowPlayingEntity

@Database(entities = [NowPlayingEntity::class], version = 1 , exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun MoviesDao(): MoviesDao

}