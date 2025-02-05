package ir.androidcoder.domain.base

sealed class Result<out T: Any> {

    data class Success<out T: Any>(val data: T) : Result<T>()

    data class Error(val error: ir.androidcoder.domain.base.Error) : Result<Nothing>()
}