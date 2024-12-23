package ir.androidcoder.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import ir.androidcoder.data.local.MoviesDao
import ir.androidcoder.data.mapper.toDB
import ir.androidcoder.data.remote.TMDBApiService
import ir.androidcoder.domain.entities.MoviesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPagingApi::class)
class NowPlayingMediator(private val api: TMDBApiService, private val dao: MoviesDao , private val auth : String) : RemoteMediator<Int , MoviesEntity>(){
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MoviesEntity>
    ): MediatorResult {

        val page = when(loadType){
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = false)
            LoadType.APPEND -> {
                val lastItem = state.pages.lastOrNull{it.data.isEmpty()}?.data?.lastOrNull()
                val nextPage = lastItem?.id?.plus(1) ?: 1
                nextPage
            }
        }

        return try {
            val response = api.getNowPlayingMovies(page = page , authorization = auth)
            Log.v("testDataNow" , response.body()?.results.toString())
            if (response.isSuccessful){
                response.body()?.results.let { data ->
                    val dataDb = data!!.map { it.toDB() }
                    withContext(Dispatchers.IO) {
                        dao.insertNowPlaying(dataDb)
                    }
                }
                MediatorResult.Success(endOfPaginationReached = response.body()?.results?.isEmpty() ?: true)
            }else{
                MediatorResult.Error(Exception("Error fetching data"))
            }
        }catch (e : Exception){
            MediatorResult.Error(e)
        }

    }

}