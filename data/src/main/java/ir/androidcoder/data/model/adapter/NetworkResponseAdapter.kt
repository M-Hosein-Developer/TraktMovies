package ir.androidcoder.data.model.adapter

import retrofit2.Call
import java.lang.reflect.Type
import okhttp3.ResponseBody
import retrofit2.CallAdapter
import retrofit2.Converter

class NetworkResponseAdapter<S : Any , E : Any>(
    private val successType : Type,
    private val errorBodyConvertor : Converter<ResponseBody , E>
) : CallAdapter<S , Call<NetworkResponse<S , E>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<NetworkResponse<S, E>> =
        NetworkResponseCall(call , errorBodyConvertor)


}