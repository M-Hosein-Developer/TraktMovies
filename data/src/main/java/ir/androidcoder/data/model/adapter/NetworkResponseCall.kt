package ir.androidcoder.data.model.adapter

import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.net.HttpURLConnection.HTTP_BAD_REQUEST
import java.net.HttpURLConnection.HTTP_INTERNAL_ERROR
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED

internal class NetworkResponseCall<S : Any , E : Any>(
    private val delegate : Call<S>,
    private val errorConverter : Converter<ResponseBody , E>
) : Call<NetworkResponse<S , E>> {

    override fun clone(): Call<NetworkResponse<S, E>> =
        NetworkResponseCall(delegate.clone() , errorConverter)

    override fun execute(): Response<NetworkResponse<S, E>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean =delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

    override fun enqueue(callback: Callback<NetworkResponse<S, E>>) = delegate.enqueue(object : Callback<S>{
        override fun onResponse(call: Call<S>, response: Response<S>) {

            val body = response.body()
            val code = response.code()
            val error = response.errorBody()

            if (response.isSuccessful) {
                body?.let {
                    callback.onResponse(
                        this@NetworkResponseCall,
                        Response.success(NetworkResponse.Success(body))
                    )
                } ?: kotlin.run {
//                    // Response is successful but the body is null
//                    callback.onResponse(
//                        this@NetworkResponseCall,
//                        Response.success(NetworkResponse.SuccessWithOutBody)
//                    )
                }

            } else {
                val errorBody = try {
                    errorConverter.convert(error)
                } catch (ex: Exception) {
                    null
                }

                when (code) {
                    HTTP_UNAUTHORIZED -> {
//                        callback.onResponse(
//                            this@NetworkResponseCall,
//                            Response.success(NetworkResponse.AuthenticationError(errorBody, code))
//                        )
                    }
                    in HTTP_BAD_REQUEST until HTTP_INTERNAL_ERROR -> {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.ApiError(errorBody, code))
                        )
                    }
                    else -> {
                        return callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.ApiError(null, code))
                        )
                    }
                }
            }

        }

        override fun onFailure(call: Call<S>, t: Throwable) {

        }

    })


}