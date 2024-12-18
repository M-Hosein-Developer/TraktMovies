package ir.androidcoder.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.androidcoder.data.local.entity.NowPlayingEntity

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlaying(data : List<NowPlayingEntity>)

    @Query("SELECT * FROM NowPlayingEntity")
    fun getAllNowPlaying() : PagingSource<Int , NowPlayingEntity>

}