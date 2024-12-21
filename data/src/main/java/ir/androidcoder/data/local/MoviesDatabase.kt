package ir.androidcoder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.androidcoder.data.local.entity.NowPlayingEntity
import ir.androidcoder.data.local.entity.PopularEntity
import ir.androidcoder.data.local.entity.TopRateEntity
import ir.androidcoder.data.local.entity.UpcomingEntity

@Database(entities = [NowPlayingEntity::class , PopularEntity::class , TopRateEntity::class , UpcomingEntity::class], version = 1 , exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun MoviesDao(): MoviesDao

}