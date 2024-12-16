package ir.androidcoder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [], version = 1 , exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun MoviesDao(): MoviesDao

}