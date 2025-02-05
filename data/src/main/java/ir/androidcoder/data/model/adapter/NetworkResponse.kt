package ir.androidcoder.data.model.adapter

sealed class NetworkResponse<out T : Any , out U : Any> {

    data class Success<T : Any>(val body : T) : NetworkResponse<T , Nothing>()

    data class ApiError<U : Any>(val apiErrorBody : U? , val code : Int) : NetworkResponse<Nothing , U>()

    data class NetworkError(val error : Throwable?) : NetworkResponse<Nothing , Nothing>()

    data class UnknownError(val error: Throwable?) : NetworkResponse<Nothing , Nothing>()

}

typealias GenericResponse<S> = NetworkResponse<S , ResponseError>