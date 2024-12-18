package ir.androidcoder.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import ir.androidcoder.data.local.MoviesDao
import ir.androidcoder.data.mapper.toDB
import ir.androidcoder.data.remote.TMDBApiService
import ir.androidcoder.domain.entities.TopRateDEntity

@OptIn(ExperimentalPagingApi::class)
class TopRateMediator(private val api: TMDBApiService, private val dao: MoviesDao, private val auth : String) : RemoteMediator<Int , TopRateDEntity>(){
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TopRateDEntity>
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
            val response = api.getPopularMovies(page = page , authorization = auth)
            if (response.isSuccessful){
                response.body()!!.results.let { data ->
                    dao.insertPopular(data.map { it.toDB()})
                }
                MediatorResult.Success(endOfPaginationReached = response.body()!!.results.isEmpty())
            }else{
                MediatorResult.Error(Exception("Error fetching data"))
            }
        }catch (e : Exception){
            MediatorResult.Error(e)
        }

    }

}