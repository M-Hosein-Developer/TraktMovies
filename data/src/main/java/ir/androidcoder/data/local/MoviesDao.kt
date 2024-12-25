package ir.androidcoder.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.androidcoder.data.local.entity.NowPlayingEntity
import ir.androidcoder.data.local.entity.PopularEntity
import ir.androidcoder.data.local.entity.TopRateEntity
import ir.androidcoder.data.local.entity.UpcomingEntity
import ir.androidcoder.data.local.entity.WatchListEntity

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlaying(data : List<NowPlayingEntity>)

    @Query("SELECT * FROM NowPlayingEntity")
    fun getAllNowPlaying() : PagingSource<Int , NowPlayingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopular(data : List<PopularEntity>)

    @Query("SELECT * FROM PopularEntity")
    fun getAllPopular() : PagingSource<Int , PopularEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRate(data : List<TopRateEntity>)

    @Query("SELECT * FROM TopRateEntity")
    fun getAllTopRate() : PagingSource<Int , TopRateEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpcoming(data : List<UpcomingEntity>)

    @Query("SELECT * FROM UpcomingEntity")
    fun getAllUpcoming() : PagingSource<Int , UpcomingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchList(data :WatchListEntity)

    @Query("SELECT * FROM WatchListEntity")
    suspend fun getAllWatchList() : PagingSource<Int , WatchListEntity>

    @Query("SELECT * FROM WatchListEntity WHERE title LIKE '%' || :title || '%' COLLATE NOCASE")
    suspend fun getWatchListByTitle(title : String)

}